package com.dq.aquaranth.emp.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 현재 로그인한 사원의 모든 회사, 부서, 사원 정보 DTO 입니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpLoginDTO {
    private String empName;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String username;
    private Long empNo;
    private String companyName;
    private Long companyNo;
    private String deptName;
    private Long deptNo;
    private String empRank;
    private Long orgaNo;
}
