package com.dq.aquaranth.emp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
    private Long empNo;
    @NotBlank
    private String empName, username, password;
    private String gender, empPhone, empAddress, empProfile, email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDate firstHiredate, lastRetiredate;
}
