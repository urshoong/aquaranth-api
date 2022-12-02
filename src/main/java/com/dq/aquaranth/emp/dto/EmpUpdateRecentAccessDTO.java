package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpUpdateRecentAccessDTO {
    private String lastLoginIp;
    private LocalDateTime lastLoginTime;
    private Long empNo;
}
