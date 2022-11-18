package com.dq.aquaranth.emp.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class EmpInsertInformationDTO {

    private String regUser, empRole;

    //사원 정보
    private Long empNo;
    @NotBlank
    private String empName, username, password;
    private String gender, empPhone, empAddress, email;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDate firstHiredate, lastRetiredate;

    //부서 정보
    private Long deptNo;

    //조직 정보
    private Long orgaNo;
    private String orgaType;

    //사원 조직 매핑
    private LocalDate hiredate;
    private String empRank;

}
