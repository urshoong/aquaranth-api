package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.DeptDTO2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper2 {
    public DeptDTO2 select(Long deptNo);

    public int insert(DeptDTO2 deptDTO2);

    public int delete(Long deptNo);

    public int update(DeptDTO2 deptDTO2);

    public List<DeptDTO2> getList(@Param("skip") int skip, @Param("size") int size);

    DeptDTO2 findByUsername(String username);
}
