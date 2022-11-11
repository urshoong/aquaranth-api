package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyModifyDTO {
    private Long companyNo;
    private String companyName, companyAddress, companyTel, ownerName, modUser;
    private Boolean companyUse;
}
