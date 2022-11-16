package com.dq.aquaranth.mygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEmpListDTO {

    /**
     * 즐겨찾기에 등록된 모든 사원 정보 담을 DTO
     * 사원 이름, 직급, 아이디, 전화번호, 이메일
     */

    private String empName, empRank, username, empPhone, email;
    private String orgaInfo;
}
