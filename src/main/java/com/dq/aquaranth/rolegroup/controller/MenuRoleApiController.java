package com.dq.aquaranth.rolegroup.controller;

import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.rolegroup.domain.MenuRole;
import com.dq.aquaranth.rolegroup.dto.MenuRoleInsertReqDTO;
import com.dq.aquaranth.rolegroup.dto.MenuRoleLnbDTO;
import com.dq.aquaranth.rolegroup.service.MenuRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu-role")
@Log4j2
public class MenuRoleApiController {
    private final MenuRoleService menuRoleService;

    @GetMapping("")
    public List<MenuRoleLnbDTO> getMenuRoles(@RequestParam Long roleGroupNo, @RequestParam String moduleCode) {
        return menuRoleService.findByRoleGroupNoAndModuleCode(roleGroupNo, moduleCode);
    }

    @PostMapping("")
    public void save(@RequestBody MenuRoleInsertReqDTO reqDTO, Authentication authentication) {
        Long roleGroupNo = reqDTO.getRoleGroupNo();
        String moduleCode = reqDTO.getModuleCode();

        List<MenuRole> insertMenuRoles = new ArrayList<>();

        for (Long menuNo : reqDTO.getMenuRoles()) {
            insertMenuRoles.add(MenuRole.builder()
                    .menuNo(menuNo)
                    .roleGroupNo(roleGroupNo)
                    .regUser(authentication.getName())
                    .regDate(LocalDateTime.now())
                    .build());
        }

        menuRoleService.save(insertMenuRoles, moduleCode, roleGroupNo);
    }
}
