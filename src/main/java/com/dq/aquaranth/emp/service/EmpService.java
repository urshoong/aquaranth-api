package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;

import java.util.List;

public interface EmpService {
    List<EmpDTO> empList();

    EmpDTO empRead(Long empNo);

    Long empRegister(EmpDTO empInsertDTO);

    Long empModify(EmpUpdateDTO empUpdateDTO);

    Long empRemove(Long empNo);
}
