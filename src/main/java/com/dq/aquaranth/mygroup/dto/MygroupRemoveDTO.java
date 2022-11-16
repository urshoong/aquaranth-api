package com.dq.aquaranth.mygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MygroupRemoveDTO {

    /**
     * 로그인한 사원의 마이그룹 삭제 시 필요한 DTO
     * 로그인한 사원의 아이디, 삭제할 마이그룹 번호
     */

    private String username;
    private Long mygroupNo;
}
