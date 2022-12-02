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
    //등록시 필요한 정보
    //상위부서번호, 회사번호, 부서이름, 부서약칭, 사용자이름
    private String deptName, deptDesc, username;
    private Long upperDeptNo, companyNo, deptNo;
}
