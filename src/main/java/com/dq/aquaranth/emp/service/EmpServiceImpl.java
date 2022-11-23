package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private final EmpMappingMapper empMappingMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<EmpDTO> findAll() {
        return empMapper.findAll();
    }

    @Override
    public EmpDTO findById(Long empNo) {
        return empMapper.findById(empNo);
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
    public List<EmpSelectOrga> findAllOrga(Long empNo) {
        return empMapper.orgaFindById(empNo);
    }

    @Override
    public Long updateFile(EmpFileDTO empFileDTO) {
        return empMapper.updateFile(empFileDTO);
    }

    /**
     * 로그인한 회원 가져오기
     */
    @Override
    public EmpLoginEmpDTO findLoginInformationByUsername(String username) {
        //모든 정보 이름이 들어있는 DTO. 실행해서 나온 모든 결과 담는 DTO
        List<EmpLoginDTO> list = empMapper.findLoginInformationByUsername(username);

        //EMP관련 정보 담는 DTO
        EmpLoginEmpDTO result = EmpLoginEmpDTO.builder().build();

        //모든거 담겨잇는 DTO를 foreach돌려서 데이터 파싱(처리)
        list.forEach(empLoginDTO -> {
            result.setEmpName(empLoginDTO.getEmpName());
            result.setLastLoginTime(empLoginDTO.getLastLoginTime());
            result.setLastLoginIp(empLoginDTO.getLastLoginIp());
            result.setUsername(empLoginDTO.getUsername());
            result.setEmpNo(empLoginDTO.getEmpNo());

            //담겨잇는 데이터에서 회사 번호를 가져온다.
            Long companyNo = empLoginDTO.getCompanyNo();

            log.info("companyNo : " + companyNo);

            //회사 리스트 꺼내기. (회사 정보만 담겨있다. 그리고 부서 리스트정보를 담고있는 DTO가 담긴 map)
            Map<Long, EmpLoginCompanyDTO> companyList = result.getCompanyList();

            //호ㅣ사 리스트가 비어잇으면, 빈 map을 만들어서? 회사 정보를 set해준다.
            if(companyList == null){
                log.info("회사 정보가 비어있음");
                companyList = new HashMap<>();
                result.setCompanyList(companyList);
            }

            //회사 목록에 해당하는 companyNo의 데이터가 없으면 만들어준다. (put)
            if(!companyList.containsKey(companyNo)){
                EmpLoginCompanyDTO tempCompanyDTO = EmpLoginCompanyDTO.builder()
                        .companyNo(empLoginDTO.getCompanyNo())
                        .companyName(empLoginDTO.getCompanyName())
                        .build();
                companyList.put(companyNo, tempCompanyDTO); //해당하는 companyNo에 대한 데이터(tempDTO)를 만들어서
                                                            //// companyList에 넣는다.
            }

            //회사 목록에서 companyNo를 가져와서 해당 회사 정보가 담긴 DTO에 담는다.
            EmpLoginCompanyDTO companyDTO = companyList.get(companyNo);

            //정보를 다 들고 있는 DTO에서 부서번호를 가져온다.
            Long deptNo = empLoginDTO.getDeptNo();

            log.info("deptNO : " + deptNo);

            //회사 DTO에서 부서 목록을 꺼내서 map에 넣는다.
            Map<Long, EmpLoginDepartmentDTO> deptList = companyDTO.getDeptList();

            // 부서 목록이 null 이라면 비어있는 map을 만들고, 회사 DTO에 부서 목록을 set해준다.
            if(deptList == null){
                log.info("부서 정보가 비어있음");
                deptList = new HashMap<>();
                companyDTO.setDeptList(deptList);
            }

            //부서 목록에 deptNo정보가 없다면 만들어서 넣어준다.
            if(!deptList.containsKey(deptNo)){
                EmpLoginDepartmentDTO tempDeptDTO = EmpLoginDepartmentDTO.builder()
                        .deptNo(deptNo)
                        .deptName(empLoginDTO.getDeptName())
                        .orgaNo(empLoginDTO.getOrgaNo())
                        .info(empLoginDTO.getInfo())
                        .empRank(empLoginDTO.getEmpRank())
                        .build();
                deptList.put(deptNo, tempDeptDTO);
            }
        });

        log.info(result);

        return result;
    }

    @Override
    public Long insertEmp(EmpDTO empDTO) {
        empDTO.setPassword(passwordEncoder.encode(empDTO.getPassword()));

        return empMapper.insertEmp(empDTO);
    }

    @Override
    public List<OrgaTreeDTO> selectDeptPath() {
        List<OrgaTreeDTO> list = empMapper.selectDeptPath();
//
//        for (int i = 0; i < list.size(); i++) {
//            StringTokenizer st = new StringTokenizer(list);
//        }

        return null;
    }
}
