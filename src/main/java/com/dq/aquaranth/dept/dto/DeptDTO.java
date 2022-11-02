package com.dq.aquaranth.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO {

    private Long upper_dept_no;
    private String dept_name;
    private boolean dept_use, dept_management;
    private int dept_sort;

}
