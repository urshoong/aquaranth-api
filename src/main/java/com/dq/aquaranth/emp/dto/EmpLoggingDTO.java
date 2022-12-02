package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 선택한 부서에따른 달라지는 정보가 담기는 DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpLoggingDTO {
    private Long loginDept;
    private Long loginCompany;
    private String loginEmpRank;
    private String hierarchy;
}
