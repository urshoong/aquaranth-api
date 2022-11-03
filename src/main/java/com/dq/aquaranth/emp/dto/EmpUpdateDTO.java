package com.dq.aquaranth.emp.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사원 정보를 수정했을 때,
 * 수정한 정보로 값을 업데이트 하기 위한 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpUpdateDTO {
    private Long empNo;
    private String empName, empPhone, empAddress, empProfile, email;
    private LocalDate lastRetiredate;
}
