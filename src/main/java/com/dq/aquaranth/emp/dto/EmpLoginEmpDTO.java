package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginEmpDTO {
    private String empName;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String username;
    private Long empNo;

    private List<EmpLoginCompanyDTO> companyList;
}
