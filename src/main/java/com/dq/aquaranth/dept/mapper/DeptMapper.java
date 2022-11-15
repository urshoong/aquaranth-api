package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.DepartmentDTO;
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

    /**
     * 선택한 회사의 부서 목록을 조회합니다.
     *
     * @param companyNo : 선택된 회사 번호
     */
    List<DepartmentDTO> findByCompanyNo(Long companyNo);
}
