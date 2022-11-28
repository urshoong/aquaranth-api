package com.dq.aquaranth.dept.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptRegisterDTO {


    private int companyNo, depth;
    private String deptName, deptDesc, regUser;
    private boolean mainFlag;
    private Long upperDeptNo;



//    companyNo
//            deptName
//    deptDesc
//            depth
//    mainFlag
//    regUser
//            upperDeptNo

}
