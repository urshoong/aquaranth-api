package com.dq.aquaranth.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long empNo;
    private String empName, username, password, gender, empPhone, empAddress, empProfile, email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDate firstHiredate;
    private LocalDate lastRetiredate;
}
