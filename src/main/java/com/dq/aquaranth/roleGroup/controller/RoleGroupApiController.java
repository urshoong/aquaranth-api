package com.dq.aquaranth.roleGroup.controller;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupModifyReqDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import com.dq.aquaranth.roleGroup.service.RoleGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role-group")
@Log4j2
public class RoleGroupApiController {
    private final RoleGroupService roleGroupService;

    @GetMapping("/")
    public List<RoleGroupDTO> getRoleGroupList() {
        return roleGroupService.findAll();
    }

    @GetMapping("/{roleGroupNo}")
    public RoleGroupDTO getRoleGroup(@PathVariable Long roleGroupNo) {
        return roleGroupService.findById(roleGroupNo);
    }

    @PostMapping("/{roleGroupNo}")
    public void modifyRoleGroup(@PathVariable Long roleGroupNo,
                                @RequestBody RoleGroupModifyReqDTO modifyReqDTO) {

        RoleGroupUpdateDTO updateDTO = RoleGroupUpdateDTO.builder()
                .roleGroupNo(roleGroupNo)
                .roleGroupUse(modifyReqDTO.isRoleGroupUse())
                .roleGroupName(modifyReqDTO.getRoleGroupName())
                .build();

        roleGroupService.update(updateDTO);
    }
}
