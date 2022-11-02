package com.dq.aquaranth.emp.mapper;


import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;

import java.util.List;

public interface EmpMapper {
    List<EmpDTO> empList();

    EmpDTO empRead(Long empNo);

    Integer empInsert(EmpDTO empDTO);

    Integer empModify(EmpUpdateDTO empUpdateDTO);
}
