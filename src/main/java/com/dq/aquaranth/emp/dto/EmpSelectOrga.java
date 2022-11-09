package com.dq.aquaranth.emp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpSelectOrga {
    private String companyName, dname, empRank, companyTel, companyAddress;
    private Long empNo;
    private LocalDate hiredate, retireddate;
}
