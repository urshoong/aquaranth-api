package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpService {
    List<EmpDTO> empList();

    EmpDTO empRead(Long empNo);

    Long empModify(EmpUpdateDTO empUpdateDTO);

    Long empRemove(Long empNo);

    ////////////insert
    void empRegister(EmpInsertInformationDTO empInsertInformationDTO);

    List<EmpSelectOrga> empOrgaList(Long empNo);



}
