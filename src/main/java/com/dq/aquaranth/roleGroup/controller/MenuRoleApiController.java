package com.dq.aquaranth.roleGroup.controller;

import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.roleGroup.domain.MenuRole;
import com.dq.aquaranth.roleGroup.dto.MenuRoleInsertReqDTO;
import com.dq.aquaranth.roleGroup.service.MenuRoleService;
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

    @PostMapping("")
    public void save(@RequestBody MenuRoleInsertReqDTO reqDTO, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        List<MenuRole> insertMenuRoles = new ArrayList<>();

        for (long menuNo : reqDTO.getMenuNoList()) {
            insertMenuRoles.add(MenuRole.builder()
                    .menuNo(menuNo)
                    .roleGroupNo(reqDTO.getRoleGroupNo())
                    .regUser(customUser.getEmpDTO().getEmpName())
                    .regDate(LocalDateTime.now())
                    .build());
        }

        menuRoleService.save(insertMenuRoles, reqDTO.getModuleCode());
    }
}
