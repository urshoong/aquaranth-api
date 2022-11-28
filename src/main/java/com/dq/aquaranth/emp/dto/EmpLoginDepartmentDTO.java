package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginDepartmentDTO {
    private String deptName;
    private Long deptNo;
    private String empRank;
    private String info;
    private Long orgaNo;
}
