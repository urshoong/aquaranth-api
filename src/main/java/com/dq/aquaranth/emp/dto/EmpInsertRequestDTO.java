package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * 사원이 추가 되었을 때,
 * 정보를 저장하기 위한 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpInsertRequestDTO {
    private String empName, username, password, gender, empPhone, empAddress, empProfile, email;
    private LocalDate firstHiredDate;
}
