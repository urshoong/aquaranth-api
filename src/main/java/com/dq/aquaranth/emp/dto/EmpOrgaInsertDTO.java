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
public class EmpOrgaInsertDTO {
    private Long orgaNo, empNo, upperOrgaNo, deptNo;
    private String empRole, empRank, orgaType, regUser;
    private LocalDate regDate;
}
