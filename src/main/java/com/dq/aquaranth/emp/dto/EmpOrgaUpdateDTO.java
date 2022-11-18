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
public class EmpOrgaUpdateDTO {
    private Long orgaNo, deptNo, upperOrgaNo, companyNo, empNo;
    private String empRank, empRole, modUser, companyAddress, companyName, companyTel, deptName;
    private boolean deptMain;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate retiredDate, hiredDate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime modDate;
}
