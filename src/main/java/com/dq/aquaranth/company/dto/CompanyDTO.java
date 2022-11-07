package com.dq.aquaranth.company.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long companyNo, orgaNo;
    private String companyName, companyAddress, companyTel, ownerName, businessNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate foundingDate;
    private Boolean companyUse;
}
