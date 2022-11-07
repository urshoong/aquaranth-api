package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO;

import java.util.List;

public interface DeptMapper {

    int insert(DeptDTO deptDTO);

    DeptDTO select(Long DeptNo);

    int update(DeptDTO deptDTO);

    int delete(Long deptNo);

    List<DeptDTO> list(DeptCriteria deptCriteria);

    List<DeptDTO> deptList();
}
