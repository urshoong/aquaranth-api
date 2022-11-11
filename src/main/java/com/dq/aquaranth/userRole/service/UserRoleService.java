package com.dq.aquaranth.userRole.service;

import com.dq.aquaranth.userRole.dto.*;

import java.util.List;

public interface UserRoleService {
    List<UserRoleCompanyDTO> findCompany(Long companyNo);
    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);
    List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO);
}
