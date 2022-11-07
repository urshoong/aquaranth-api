package com.dq.aquaranth.emp.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 클라이언트 쪽에서 사원 정보 수정을 요청했을 때,
 * 요청한 값을 담아 보내주기 위한 DTO
 */
@Data
public class EmpModifyRequestDTO {
    private Long empNo;
    private String empName, empPhone, empAddress, empProfile, email;
    private LocalDate lastRetiredate;
}
