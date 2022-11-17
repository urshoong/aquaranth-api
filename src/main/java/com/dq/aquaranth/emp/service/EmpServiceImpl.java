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
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;

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




}
