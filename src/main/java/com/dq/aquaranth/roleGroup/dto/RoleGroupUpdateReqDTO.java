package com.dq.aquaranth.roleGroup.dto;

import lombok.Data;

@Data
public class RoleGroupUpdateReqDTO {
    private Long roleGroupNo;
    private String roleGroupName;
    private boolean roleGroupUse;
}
