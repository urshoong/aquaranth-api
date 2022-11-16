package com.dq.aquaranth.roleGroup.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuRoleInsertReqDTO {
    private Long roleGroupNo;
    private String moduleCode;
    List<MenuRoleLnbDTO> dtoList;
}
