package com.dq.aquaranth.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyOrgaDTO {
    private Long orgaNo, upperOrgaNo;
    private String orgaType;
}
