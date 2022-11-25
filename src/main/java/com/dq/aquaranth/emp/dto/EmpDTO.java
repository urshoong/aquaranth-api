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
    private String gender, empPhone, empAddress, email, lastLoginIp,
                   regUser, regDate, modUser, modDate;
    private boolean empUse;
    private LocalDateTime lastLoginTime;
    private LocalDate firstHiredDate, lastRetiredDate;
}
