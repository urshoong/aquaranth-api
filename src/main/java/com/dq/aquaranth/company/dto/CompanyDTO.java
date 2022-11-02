package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Long companyNo;
    private String companyName, companyAddress, companyTel, ownerName, businessNumber;
    private LocalDate foundingDate;
    private Boolean companyUse;
}
