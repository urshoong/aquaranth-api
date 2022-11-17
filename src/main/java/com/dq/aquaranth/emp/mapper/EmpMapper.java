package com.dq.aquaranth.emp.mapper;


import com.dq.aquaranth.emp.dto.*;

import java.util.List;

public interface EmpMapper {

    /**
     * 모든 사원 정보를 조회합니다.
     */
    List<EmpDTO> findAll();

    /**
     * 선택된 사원 정보를 조회합니다.
     *
     * @param empNo : 찾으려는 사원 번호
     * @return : 선택된 사원에 대한 모든 정보
     */
    EmpDTO findById(Long empNo);

    /**
     *
     * @return
     */
    List<EmpSelectOrga> orgaFindById(Long empNo);

    /**
     * 새로운 사원을 추가합니다.
     *
     * @param empInsertDTO : 추가될 사원 객체
     */
    Long insert(EmpDTO empInsertDTO);


    /**
     * 조직에 사원에 대한 값을 추가합니다.
     *
     * @param empOrgaInsertDTO : 조직에 추가될 사원 정보 객체
     */
    Long orgaInsert(EmpOrgaDTO empOrgaInsertDTO);


    /**
     * 사원 번호로 사원 정보를 수정합니다.
     *
     * @param empUpdateDTO : 수정할 사원 객체
     */
    Long update(EmpUpdateDTO empUpdateDTO);

    /**
     * 조직 번호로 조직 정보를 수정합니다.
     * @param empOrgaUpdateDTO : 수정할 사원의 조직 객체
     */
    Long orgaUpdate(EmpOrgaUpdateDTO empOrgaUpdateDTO);


    EmpDTO findByUsername(String username);


}
