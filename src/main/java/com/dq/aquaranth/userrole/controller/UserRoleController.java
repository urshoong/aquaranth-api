package com.dq.aquaranth.userrole.controller;

import com.dq.aquaranth.userrole.dto.*;
import com.dq.aquaranth.userrole.service.UserRoleService;
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
    public List<UserRoleCompanyDTO> findCompany(@PathVariable Long companyNo) {
        return userRoleService.findCompany(companyNo);
    }

    @GetMapping("/roleGroupList")
    public List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO) {
        return userRoleService.findRoleGroupByCompanyName(userRoleReqRoleGroupBasedListDTO);
    }

    @GetMapping("/roleGroupUserList")
    public List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO) {
        return userRoleService.findOrgaByRoleGroupNo(userRoleReqGroupBasedUserListDTO);
    }

    @PostMapping("/insertOrgaRole")
    public Integer insertOrgaRole(@RequestBody UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO) {
        log.info(userRoleReqInsertOrgaRoleDTO);
        return userRoleService.insertUserRole(userRoleReqInsertOrgaRoleDTO);
    }

    @PostMapping("/removeOrgaRole")
    public Integer removeOrgaRole(@RequestBody UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO) {
        return userRoleService.removeUserRole(userRoleReqRemoveOrgaRoleDTO);
    }

    @GetMapping("/findUserListByOrgaNo")
    public List<UserRoleUserListBasedDTO> findUserListByOrgaNo(UserRoleReqUserListBasedDTO userRoleReqUserListBasedDTO) {
        return userRoleService.findUserListByOrgaNo(userRoleReqUserListBasedDTO);
    }

    @GetMapping("/findByRoleGroupByUser/{orgaNo}")
    public List<UserRoleUserBasedRoleGroupDTO> findByRoleGroupByUser(@PathVariable Long orgaNo) {
        return userRoleService.findByRoleGroupByUser(orgaNo);
    }
}
