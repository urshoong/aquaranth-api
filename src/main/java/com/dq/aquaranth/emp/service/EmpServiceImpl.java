package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
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
            ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                    .filename(empDTO.getUuid() + empDTO.getFilename())
                    .build();

            try {
                empDTO.setProfileUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return empDTOList;
    }

    @Override
    public EmpDTO findById(Long empNo) {
        EmpDTO empDTO = empMapper.findById(empNo);

        ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                .filename(empDTO.getUuid() + empDTO.getFilename())
                .build();

        try {
            empDTO.setProfileUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return empDTO;
    }

    @Override
    public Long update(EmpUpdateDTO empUpdateDTO)
    {
        return empMapper.update(empUpdateDTO);
    }

    @Override
    public Long orgaUpdate(EmpOrgaUpdateDTO empOrgaUpdateDTO) {
        Long re = empMapper.orgaUpdate(empOrgaUpdateDTO);
        log.info("service result : "+re);
        return re;
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

        List<EmpLoginEmpDTO> result = empMapper.findLoginUser(username);

        Long dept = userSessionService.findUserInfoInRedis(username).getDept().getDeptNo();
        Long company = userSessionService.findUserInfoInRedis(username).getCompany().getCompanyNo();

        String finalIp = ip;
        result.forEach(emp -> {
            emp.setLoginIp(finalIp);
            emp.setLoginDept(dept);
            emp.setLoginCompany(company);
        });

        return result;
    }

}
