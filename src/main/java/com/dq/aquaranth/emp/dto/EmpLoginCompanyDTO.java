package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpLoginCompanyDTO {
    private String companyName;
    private Long companyNo;

    private Map<Long, EmpLoginDepartmentDTO> deptList;
}
