package com.dq.aquaranth.dept.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO {
    private Long deptNo;
    private Long upperDeptNo;
    private String deptName, deptDesc, regUser, modUser;
    private boolean delFlag, mainFlag;
    private int ord, depth, companyNo;
    private LocalDateTime regDate, modDate;
    private Long lastDno;
    /*
*/
}
