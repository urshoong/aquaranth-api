package com.dq.aquaranth.emp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpOrgaListUpdateDTO {
    private Long companyNo, empNo, orgaNo;
    private String companyAddress, companyName, companyTel, deptName, empRank, empRole;
    private boolean deptMain;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredDate, retiredDate;
}
