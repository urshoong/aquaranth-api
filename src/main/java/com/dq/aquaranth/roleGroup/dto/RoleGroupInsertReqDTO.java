package com.dq.aquaranth.roleGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleGroupInsertReqDTO {
    private String roleGroupName;
    private String companyName;
    private boolean roleGroupUse;
}
