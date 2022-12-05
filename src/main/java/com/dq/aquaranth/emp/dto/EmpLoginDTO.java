package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
