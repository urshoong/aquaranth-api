package com.dq.aquaranth.userrole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleReqRemoveOrgaRoleDTO {
    Long companyNo, roleGroupNo, orgaNo;
    List<Long> removeOrgaRoleList;
}
