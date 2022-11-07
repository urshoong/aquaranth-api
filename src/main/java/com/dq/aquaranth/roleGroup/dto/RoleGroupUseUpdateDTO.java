package com.dq.aquaranth.roleGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleGroupUseUpdateDTO {
    private Long roleGroupNo;
    private boolean roleGroupUse;
}
