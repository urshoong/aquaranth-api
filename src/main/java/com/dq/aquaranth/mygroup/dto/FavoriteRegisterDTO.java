package com.dq.aquaranth.mygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRegisterDTO {

    /**
     * 마이그룹에 사원들을 즐겨찾기 할 때 필요한 DTO
     * 로그인한 아이디, 즐겨찾기할 사원번호
     */
    private Long empNo;
    private String username;
}
