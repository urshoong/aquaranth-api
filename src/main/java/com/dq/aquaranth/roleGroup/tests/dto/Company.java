package com.dq.aquaranth.roleGroup.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private Long companyNo;
    private String companyName;
}
