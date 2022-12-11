package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.menu.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.menu.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.menu.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;
    private final EmpMappingMapper empMappingMapper;
    private final PasswordEncoder passwordEncoder;
    private final ObjectStorageService objectStorageService;
    private final UserSessionService userSessionService;

    @Override
    public List<EmpDTO> findAll() {
        List<EmpDTO> empDTOList = empMapper.findAll();

        empDTOList.forEach(empDTO -> {
            if(empDTO.getUuid() != null && empDTO.getFilename() != null){
                ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                        .filename(empDTO.getUuid() + empDTO.getFilename())
                        .build();

                try {
                    empDTO.setProfileUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return empDTOList;
    }

    @Override
    public EmpDTO findById(Long empNo) {
        EmpDTO empDTO = empMapper.findById(empNo);

        // 파일이 없는 사원만 filename
        if(empDTO.getUuid() != null && empDTO.getFilename() != null) {
            ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                    .filename(empDTO.getUuid() + empDTO.getFilename())
                    .build();

            try {
                empDTO.setProfileUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return empDTO;
    }

    @Override
    public Long update(EmpUpdateDTO empUpdateDTO)
    {
        Long empNo = empUpdateDTO.getEmpNo();
        if(empUpdateDTO.getLastRetiredDate() != null){
            //해당 orga들 퇴사일 now로.
            //알고있는 정보 empNo, RetiredDate
            empMappingMapper.updateEmpMappingRetiredDate(empNo);
        }
        return empMapper.update(empUpdateDTO);
    }

    @Override
    public Long orgaUpdate(EmpOrgaUpdateDTO empOrgaUpdateDTO) {
        Long orgaList = empMapper.orgaUpdate(empOrgaUpdateDTO);
        log.info("service result : "+orgaList);
        return orgaList;

//        Long orgaList = empMapper.orgaUpdate(empOrgaUpdateDTO);
//        log.info("service result : "+orgaList);
//        return orgaList;
    }


    @Override
    @Transactional
    public EmpDTO insert(EmpOrgaDTO orgaReqDTO, EmpDTO empReqDTO, EmpMappingDTO mapperReqDTO) throws IllegalAccessException {
        // 이미 가입된 유저라면
        if (Objects.nonNull(empMapper.findByUsername(empReqDTO.getUsername()))) {
            log.error("이미 가입된 유저입니다. username -> " + empReqDTO.getUsername());
            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + empReqDTO.getUsername());
        }

        // 조직 테이블 insert
        empMapper.orgaInsert(orgaReqDTO);

        // 조직 테이블의 last_insert_id 저장
        Long orgaNo = orgaReqDTO.getOrgaNo();

        // 사원 테이블 insert
        empReqDTO.setPassword(passwordEncoder.encode(empReqDTO.getPassword()));
        empMapper.insert(empReqDTO);

        // 사원 테이블의 last_insert_id 저장
        Long empNo = empReqDTO.getEmpNo();

        // 사원매핑 테이블 insert
        mapperReqDTO.setOrgaNo(orgaNo);
        mapperReqDTO.setEmpNo(empNo);
        empMappingMapper.empMappingInsert(mapperReqDTO);

        return empReqDTO;
    }

    @Override
    @Transactional
    public Long empOrgaInsert(EmpOrgaDTO orgaReqDTO, EmpMappingDTO mapperReqDTO, Long empNo) {
        // 조직 테이블 insert
        empMapper.orgaInsert(orgaReqDTO);

        // 조직 테이블의 last_insert_id 저장
        Long orgaNo = orgaReqDTO.getOrgaNo();

        // 사원매핑 테이블 insert
        mapperReqDTO.setOrgaNo(orgaNo);
        mapperReqDTO.setEmpNo(empNo);

        return empMappingMapper.empMappingInsert(mapperReqDTO);
    }


    @Override
    @Transactional
    public List<EmpSelectOrga> findAllOrga(Long empNo) {
        return empMapper.orgaFindById(empNo);
    }

    @Override
    @Transactional
    public Long updateFile(MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();

        ObjectPostRequestDTO objectPostRequestDTO = ObjectPostRequestDTO.builder().filename(uuid + filename).multipartFile(multipartFileDTO.getMultipartFile()).build();

        EmpFileDTO empFileDTO = EmpFileDTO.builder().empNo(Long.valueOf(multipartFileDTO.getKey())).uuid(uuid).filename(filename).build();

        empMapper.updateProfile(empFileDTO);
        objectStorageService.postObject(objectPostRequestDTO);

        return empMapper.updateProfile(empFileDTO);
    }

    // 프로필 filename, uuid null 로 업데이트. 인데 그것이 삭제하는 것임.

    /**
     * 로그인한 회원 가져오기
     */
    @Override
    public List<EmpLoginEmpDTO> findLoginUser(String username) {
        String ip = null;

        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        // 접속한 아이디로 로그인한 사원의 정보를 찾아 계층형 구조의 값을 저장한다.
        List<EmpLoginEmpDTO> loginEmpList = empMapper.findLoginUser(username);

        String finalIp = ip;

        EmpDTO empDTO = empMapper.findByUsername(username);

        if(empDTO.getUuid() != null && empDTO.getFilename() != null) {
            ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                    .filename(empDTO.getUuid() + empDTO.getFilename())
                    .build();

            try {
                empDTO.setProfileUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // 계층 구조에 회원 정보 값을 넣는다.
        loginEmpList.forEach(emp -> {
            emp.setLoginIp(finalIp);
            emp.setProfileUrl(empDTO.getProfileUrl());
        });

        return loginEmpList;
    }

    /**
     * 현재 로그인한 사원의 정보 companyNo, deptNo, empno 두 가지 redis가져옴.
     */
    @Override
    public EmpLoggingDTO findLoggingInformation(String username) {

        Long dept = userSessionService.findUserInfoInRedis(username).getDept().getDeptNo();
        Long company = userSessionService.findUserInfoInRedis(username).getCompany().getCompanyNo();
        String empRank = userSessionService.findUserInfoInRedis(username).getEmpMapping().getEmpRank();
        Long orgaNo = userSessionService.findUserInfoInRedis(username).getEmpMapping().getOrgaNo();

        String hierarchy = empMapper.functionHierarchy(orgaNo);

        EmpLoggingDTO empLoggingDTO = EmpLoggingDTO.builder()
                .loginCompany(company)
                .loginDept(dept)
                .loginEmpRank(empRank)
                .hierarchy(hierarchy)
                .build();

        //-------------------------------------------
        String ip = null;

        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        Long empNo = userSessionService.findUserInfoInRedis(username).getEmp().getEmpNo();

        // 확인 버튼 클릭 시, emp 최근 접속 ip, 시간을 empno으로 찾아 업데이트.
        EmpUpdateRecentAccessDTO updateDTO
                = EmpUpdateRecentAccessDTO.builder()
                .lastLoginIp(ip)
                .lastLoginTime(LocalDateTime.now())
                .empNo(empNo)
                .build();

        empMapper.updateRecentAccessInfo(updateDTO);

        return empLoggingDTO;
    }

    @Override
    public Long deleteProfile(Long empNo) {

        EmpFileDTO empFileDTO = EmpFileDTO
                .builder()
                .uuid(null)
                .filename(null)
                .empNo(empNo)
                .build();

        return empMapper.updateProfile(empFileDTO);
    }

}
