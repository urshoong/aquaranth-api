package com.dq.aquaranth.dept.mapper;


import com.dq.aquaranth.dept.dto.DepartmentDTO;
import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.dto.DeptTreeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public DeptDTO select(Long deptNo);

    public int insert(DeptDTO deptDTO2);

    public int delete(Long deptNo);

    public int update(DeptDTO deptDTO2);

    public List<DeptDTO> getList(@Param("skip") int skip, @Param("size") int size);

    public List<DeptDTO> getGnoList(int gno);

    int getNextOrd(@Param("company") Integer company, @Param("parentDeptNo") Long parentDeptNo);

    void arrangeOrd(@Param("company") Integer company, @Param("ord") int ord);

    void fixOrd (@Param("deptNo") Long deptNo, @Param("ord") int ord);

    void updateLastDno(@Param("parentDeptNo") Long parentDeptNo, @Param("deptNo") Long deptNo);

    List<DeptDTO> getFromParent(@Param("upperDeptNo") Long upperDeptNo, @Param("depth") int depth);


    List<DeptTreeDTO> getTree(@Param("company") Long company );


    void insertOrga( @Param("orga") Long orga, @Param("regUser") String regUser);

    Long getLast();

    void insertOrgaMapping(@Param("deptNo") Long deptNo, @Param("orgaNo") Long orgaNo, @Param("regUser") String regUser );


    List<DeptDTO> deptList();

    /**
     * 선택한 회사의 부서 목록을 조회합니다.
     *
     * @param companyNo : 선택된 회사 번호
     */
    List<DepartmentDTO> findByCompanyNo(Long companyNo);
}
