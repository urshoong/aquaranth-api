package com.dq.aquaranth.emp.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmpDTO {
    private Long empNo;
    private String empName, username, password, gender, empPhone, empAddress, empProfile, email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDate firstHiredate, lastRetiredate;
}
