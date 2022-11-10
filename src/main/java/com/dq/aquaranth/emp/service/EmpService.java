package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface EmpService {
    List<EmpDTO> empList();

    EmpDTO empRead(Long empNo);

    Long empModify(EmpUpdateDTO empUpdateDTO);

    Long empRemove(Long empNo);

    EmpDTO insert(EmpOrgaDTO orgaReqDTO, EmpDTO reqDTO, EmpMappingDTO mapperReqDTO) throws IllegalAccessException;

    List<EmpSelectOrga> empOrgaList(Long empNo);



}
