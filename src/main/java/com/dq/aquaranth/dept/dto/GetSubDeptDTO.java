package com.dq.aquaranth.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubDeptDTO {
    private int companyNo, depth;
    private Long upperDeptNo;
}
