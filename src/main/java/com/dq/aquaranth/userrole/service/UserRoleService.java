package com.dq.aquaranth.userrole.service;

import com.dq.aquaranth.userrole.dto.*;

import java.util.List;

public interface UserRoleService {
    List<UserRoleCompanyDTO> findCompany(Long companyNo);
    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);
    List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO);
}
