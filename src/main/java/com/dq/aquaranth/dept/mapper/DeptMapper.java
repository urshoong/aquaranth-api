package com.dq.aquaranth.dept.mapper;


import com.dq.aquaranth.dept.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public DeptDTO select(Long deptNo);

    public int insert(DeptDTO deptDTO2);

    public int delete(Long deptNo);

    public int update(DeptDTO deptDTO2);

    public List<DeptDTO> getList(@Param("skip") int skip, @Param("size") int size);

    public List<DeptDTO> getGnoList(int gno);

    //===========================등록 트랜잭션======================================

    int getNextOrd(@Param("company") Integer company, @Param("parentDeptNo") Long parentDeptNo);

    void arrangeOrd(@Param("company") Integer company, @Param("ord") int ord);

    void fixOrd (@Param("deptNo") Long deptNo, @Param("ord") int ord);

    void updateLastDno(@Param("parentDeptNo") Long parentDeptNo, @Param("deptNo") Long deptNo);

    void insertOrga( @Param("orga") Long orga, @Param("regUser") String regUser);

    Long getLast();

    void insertOrgaMapping(@Param("deptNo") Long deptNo, @Param("orgaNo") Long orgaNo, @Param("regUser") String regUser );

    //===========================등록 트랜잭션======================================



    List<DeptDTO> getFromParent(@Param("upperDeptNo") Long upperDeptNo, @Param("depth") int depth);

//    트리 구조 조회
    List<DeptTreeDTO> getTree(@Param("company") Long company );

    //상위 회사 먼저 나오고 클릭하면 하위 부서 조회
    List<DeptDTO> getSubDepth(GetSubDeptDTO getSubDeptDTO);






//    List<DeptDTO> deptList();

    /**
     * 선택한 회사의 부서 목록을 조회합니다.
     *
     * @param companyNo : 선택된 회사 번호
     */
    List<DepartmentDTO> findByCompanyNo(Long companyNo);

    DeptDTO findByUsername(String username);
}
