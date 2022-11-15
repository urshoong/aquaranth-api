package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface EmpService {
    List<EmpDTO> findAll();

    EmpDTO findById(Long empNo);

    Long update(EmpUpdateDTO empUpdateDTO);

    Long orgaUpdate(EmpOrgaUpdateDTO empOrgaUpdateDTO);

    Long delete(Long empNo);

    EmpDTO insert(EmpOrgaDTO orgaReqDTO, EmpDTO reqDTO, EmpMappingDTO mapperReqDTO) throws IllegalAccessException;

    // 사원의 조직 정보 추가
    EmpOrgaInsertDTO empOrgaInsert(EmpOrgaDTO orgaReqDTO, EmpMappingDTO mapperReqDTO, Long empNo);

    List<EmpSelectOrga> findAllOrga(Long empNo);



}
