package com.dq.aquaranth.roleGroup.controller;

import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.roleGroup.domain.RoleGroup;
import com.dq.aquaranth.roleGroup.dto.RoleGroupInsertReqDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import com.dq.aquaranth.roleGroup.service.RoleGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role-group")
@Log4j2
public class RoleGroupApiController {
    private final RoleGroupService roleGroupService;

    @GetMapping("")
    public List<RoleGroup> getRoleGroupList() {
        return roleGroupService.findAll();
    }

    @GetMapping("/{roleGroupNo}")
    public RoleGroup getRoleGroup(@PathVariable Long roleGroupNo) {
        return roleGroupService.findById(roleGroupNo);
    }

    @PostMapping("")
    public RoleGroup register(@RequestBody RoleGroupInsertReqDTO reqDTO, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        RoleGroup insertRoleGroup = RoleGroup.builder()
                .roleGroupName(reqDTO.getRoleGroupName())
                .roleGroupUse(reqDTO.isRoleGroupUse())
                .companyNo(customUser.getCompanyDTO().getCompanyNo())
                .regUser(customUser.getEmpDTO().getEmpName())
                .regDate(LocalDateTime.now())
                .build();

        return roleGroupService.insert(insertRoleGroup);
    }

    @PutMapping("")
    public void modify(@RequestBody RoleGroupUpdateDTO reqDTO) {
        roleGroupService.update(reqDTO);
    }

    @DeleteMapping("/{roleGroupNo}")
    public void remove(@PathVariable Long roleGroupNo) {
        roleGroupService.deleteById(roleGroupNo);
    }
}
