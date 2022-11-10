package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import com.dq.aquaranth.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpServiceImpl implements EmpService {

    private final EmpMapper mapper;
    private final EmpMappingMapper mappingMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<EmpDTO> empList() {
        return mapper.empFindAll();
    }

    @Override
    public EmpDTO empRead(Long empNo) {
        return mapper.empFindById(empNo);
    }

    @Override
    public Long empModify(EmpUpdateDTO empUpdateDTO) {
        return mapper.empUpdate(empUpdateDTO);
    }

    @Override
    public Long empRemove(Long empNo) {
        return mapper.empDeleteById(empNo);
    }

    @Override
    @Transactional
    public EmpDTO insert(EmpOrgaDTO orgaReqDTO, EmpDTO empReqDTO, EmpMappingDTO mapperReqDTO) throws IllegalAccessException {
        // 이미 가입된 유저라면
        if (Objects.nonNull(mapper.findByUsername(empReqDTO.getUsername()))) {
            log.error("이미 가입된 유저입니다. username -> " + empReqDTO.getUsername());
            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + empReqDTO.getUsername());
        }

        // 조직 테이블 insert
        mapper.empOrgaInsert(orgaReqDTO);

        // 조직 테이블의 last_insert_id 저장
        Long orgaNo = orgaReqDTO.getOrgaNo();

        // 사원 테이블 insert
        empReqDTO.setPassword(passwordEncoder.encode(empReqDTO.getPassword()));
        mapper.empInsert(empReqDTO);

        // 사원 테이블의 last_insert_id 저장
        Long empNo = empReqDTO.getEmpNo();

        // 사원매핑 테이블 insert
        mapperReqDTO.setOrgaNo(orgaNo);
        mapperReqDTO.setEmpNo(empNo);
        mappingMapper.empMappingInsert(mapperReqDTO);

        return empReqDTO;
    }


    @Override
    public List<EmpSelectOrga> empOrgaList(Long empNo) {
        return mapper.empOrgaFindById(empNo);
    }


}
