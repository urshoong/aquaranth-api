package com.dq.aquaranth.emp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private String username;
    private Long empNo;
    private String loginIp;
//    private Long loginDept;
//    private Long loginCompany;

    private List<EmpLoginCompanyDTO> companyList;
}
