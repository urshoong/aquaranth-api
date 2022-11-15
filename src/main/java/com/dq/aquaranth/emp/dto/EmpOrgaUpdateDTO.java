package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpOrgaUpdateDTO {
    private Long orgaNo, deptNo, upperOrgaNo;
    private String empRank, empRole, modUser;
    private LocalDate modDate, retiredDate;
}
