package com.dq.aquaranth.mygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MygroupModifyDTO {

    /**
     * 로그인한 사원의 마이그룹 수정
     * 마이그룹 이름, 사원의 아이디
     */
    private String mygroupName, username, modUser;
}
