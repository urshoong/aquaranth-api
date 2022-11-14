package com.dq.aquaranth.mygroup.mapper;

import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;

public interface MygroupMapper {

    /**
     * 로그인한 사원의 마이그룹 생성
     */
    Long insert(String username);

    /**
     * 로그인한 사원의 마이그룹 수정
     */
    Long update(MygroupModifyDTO mygroupModifyDTO);

    /**
     * 로그인한 사원의 마이그룹 삭제
     */
    Long delete(String username);

}
