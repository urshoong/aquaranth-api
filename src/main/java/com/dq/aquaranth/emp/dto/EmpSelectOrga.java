package com.dq.aquaranth.emp.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 사원을 선택했을 때,
 * 조직 정보를 띄우기 위한 DTO
 */
@Data
public class EmpSelectOrga {
    private String companyName, deptName, empRank, companyTel, companyAddress, empRole;
    private Long empNo, orgaNo, companyNo;
    private boolean deptMain;
    private LocalDate hiredDate, retiredDate;
}
