package com.dq.aquaranth.emp.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpUpdateDTO {
    private Long empNo;
    private String empName, empPhone, empAddress, empProfile, email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDate lastRetiredate;
}
