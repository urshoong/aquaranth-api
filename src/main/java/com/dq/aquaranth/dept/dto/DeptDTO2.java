package com.dq.aquaranth.dept.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO2 {
    private Long deptNo;
    private Long upperDeptNo;
    private String dname, ddesc;
    private boolean delflag, mainflag;
    private int deptSort;
    private LocalDateTime regdate, updatedate;

}
