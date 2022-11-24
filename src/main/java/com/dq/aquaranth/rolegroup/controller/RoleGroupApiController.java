package com.dq.aquaranth.rolegroup.controller;

import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.dto.LoginUserInfo;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.dq.aquaranth.rolegroup.dto.RoleGroupInsertReqDTO;
import com.dq.aquaranth.rolegroup.dto.RoleGroupUpdateDTO;
import com.dq.aquaranth.rolegroup.dto.RoleGroupUpdateReqDTO;
import com.dq.aquaranth.rolegroup.service.RoleGroupService;
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
    private final UserSessionService userSessionService;

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
//      TODO:  mockup data 로그인 세션처리 완성되면 레디스에서 들고와야함.
        String username = "admin";

        LoginUserInfo loginUserInfo = userSessionService.findUserInfoInRedis(authentication.getName());

        RoleGroup insertRoleGroup = RoleGroup.builder()
                .companyNo(reqDTO.getCompanyNo())
                .roleGroupName(reqDTO.getRoleGroupName())
                .roleGroupUse(reqDTO.isRoleGroupUse())
                .regUser(username)
                .regDate(LocalDateTime.now())
                .build();

        return roleGroupService.insert(insertRoleGroup);
    }

    @PutMapping("")
    public void modify(@RequestBody RoleGroupUpdateReqDTO reqDTO, Authentication authentication) {
        //      TODO:  mockup data 로그인 세션처리 완성되면 레디스에서 들고와야함.
        String username = "admin";

        RoleGroupUpdateDTO updateDTO = RoleGroupUpdateDTO.builder()
                .roleGroupNo(reqDTO.getRoleGroupNo())
                .roleGroupName(reqDTO.getRoleGroupName())
                .roleGroupUse(reqDTO.isRoleGroupUse())
                .companyNo(reqDTO.getCompanyNo())
                .modUser(username)
                .modDate(LocalDateTime.now())
                .build();

        roleGroupService.update(updateDTO);
    }

    @DeleteMapping("/{roleGroupNo}")
    public void remove(@PathVariable Long roleGroupNo) {
        roleGroupService.deleteById(roleGroupNo);
    }
}
