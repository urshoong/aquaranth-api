package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginDTO {
    private String empName;
    private String empRank;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String username;
    private String info;
    private Map<Integer,EmpLoginCompanyDTO> companyList;
}
