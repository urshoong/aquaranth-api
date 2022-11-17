package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgaEmpDTO {

    /**
     * 해당 부서 및 회사에 소속된 모든 사원의 정보를 담을 DTO
     * 사원의 이름, 직급, 아이디, 전화번호
     */
    private String empName, empRank, username, empPhone;
}
