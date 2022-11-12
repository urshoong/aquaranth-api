package com.dq.aquaranth.userRole.controller;

import com.dq.aquaranth.userRole.dto.*;
import com.dq.aquaranth.userRole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/userrole")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping("/companyList/{companyNo}")
    public List<UserRoleCompanyDTO> findCompany(@PathVariable Long companyNo){
        return userRoleService.findCompany(companyNo);
    }

    @GetMapping("/roleGroupList")
    public List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO){
        return userRoleService.findRoleGroupByCompanyName(userRoleReqRoleGroupBasedListDTO);
    }

    @GetMapping("/roleGroupUserList")
    public List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO){
        return userRoleService.findOrgaByRoleGroupNo(userRoleReqGroupBasedUserListDTO);
    }
}
