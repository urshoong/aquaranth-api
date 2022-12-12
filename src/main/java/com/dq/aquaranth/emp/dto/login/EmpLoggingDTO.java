package com.dq.aquaranth.emp.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 접속 시 회사 선택 또는 접속 중 회사 변경 시
 * 선택한(접속중) 회사, 부서에 따라 달라지는 정보 DTO 입니다.
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
