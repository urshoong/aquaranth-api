package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgaEmpInformationDTO {

    /**
     * 해당 사원의 정보를 담은 DTO
     * 사원의 이름, 아이디, 전화번호, 이메일
     */
    private String empName, username, empPhone, email;
}
