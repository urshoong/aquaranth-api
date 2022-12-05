package com.dq.aquaranth.emp.mapper;

import com.dq.aquaranth.emp.dto.EmpMappingDTO;

public interface EmpMappingMapper {
    /**
     * 사원에 대한 조직 정보를 알기 위해
     * 사원-조직 매핑 테이블에
     * 사원 정보와 조직 정보를 추가합니다.
     *
     * @param empMappingDTO : 사원-조직 매핑 정보 객체
     */
    Long empMappingInsert(EmpMappingDTO empMappingDTO);

    // 레디스에 올릴 empMapping값. username과 deptno으로 검색한다.
    EmpMappingDTO findByUsernameAndDeptNo(String username, Long deptNo);
}
