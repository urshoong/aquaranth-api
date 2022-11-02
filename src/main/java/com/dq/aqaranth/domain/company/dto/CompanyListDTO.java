package com.dq.aqaranth.domain.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyListDTO {
    private Long companyNo;
    private String companyName, ownerName;
    private Boolean companyUse;
}
