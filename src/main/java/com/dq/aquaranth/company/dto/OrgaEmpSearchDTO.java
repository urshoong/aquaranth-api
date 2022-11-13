package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgaEmpSearchDTO {

    /**
     * 해당 부서 및 회사에 맞는 사원을 찾을 때 필요한 DTO
     * 해당 부서 및 회사의 조직번호, '사원'의 조직타입
     */

    private Long orgaNo;

    @Builder.Default
    private String orgaType = "emp";
}
