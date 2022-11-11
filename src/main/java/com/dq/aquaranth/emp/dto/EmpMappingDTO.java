package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpMappingDTO {
    private Long orgaNo, empNo;
    private String empRole, empRank, regUser, regDate, modUser, modDate;
    private LocalDate hiredDate, retiredDate;
}
