package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.EmpDTO;

import java.util.List;

public interface EmpService {
    List<EmpDTO> empList();

    EmpDTO empRead(Long empNo);

    Integer empInsert(EmpDTO empDTO);
}
