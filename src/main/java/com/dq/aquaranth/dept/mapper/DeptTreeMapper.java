package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.DeptDTO;

import java.util.List;

public interface DeptTreeMapper {

    public List<DeptDTO> getTreeModal(Long companyNo);
}
