package com.dq.aquaranth.emp.mapper;


import com.dq.aquaranth.emp.dto.EmpDTO;

import java.util.List;

public interface EmpMapper {
    List<EmpDTO> empList();

    EmpDTO empSelectOne(Long emp_no);


}
