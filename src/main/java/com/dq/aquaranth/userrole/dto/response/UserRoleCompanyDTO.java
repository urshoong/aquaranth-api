package com.dq.aquaranth.userrole.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleCompanyDTO {
    Long companyNo, orgaNo;
    String companyName;
}
