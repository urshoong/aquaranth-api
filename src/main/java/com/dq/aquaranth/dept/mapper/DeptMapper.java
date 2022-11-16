package com.dq.aquaranth.dept.mapper;

import com.dq.aquaranth.dept.dto.DeptDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public DeptDTO select(Long deptNo);

    public int insert(DeptDTO deptDTO2);

    public int delete(Long deptNo);

    public int update(DeptDTO deptDTO2);

    public List<DeptDTO> getList(@Param("skip") int skip, @Param("size") int size);

    public List<DeptDTO> getGnoList(int gno);

    int getNextOrd(@Param("gno") Integer gno, @Param("parentDeptNo") Long parentDeptNo);

    void arrangeOrd(@Param("gno") Integer gno, @Param("ord") int ord);

    void fixOrd (@Param("deptNo") Long deptNo, @Param("ord") int ord);

    void updateLastDno(@Param("parentDeptNo") Long parentDeptNo, @Param("deptNo") Long deptNo);

    List<DeptDTO> getFromParent(@Param("upperDeptNo") Long upperDeptNo, @Param("depth") int depth);
}
