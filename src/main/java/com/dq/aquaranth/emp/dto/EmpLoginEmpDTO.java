package com.dq.aquaranth.emp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 로그인한 회원 정보. 고정된 값들 (부서에 따라 변하지 않는 값들) DTO
 */
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
    private String uuid;
    private String filename;
    private String profileUrl;

    private List<EmpLoginCompanyDTO> companyList;
}
