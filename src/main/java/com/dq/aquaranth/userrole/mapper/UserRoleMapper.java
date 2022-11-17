package com.dq.aquaranth.userrole.mapper;

import com.dq.aquaranth.userrole.dto.*;

import java.util.List;

public interface UserRoleMapper {
    List<UserRoleCompanyDTO> findCompany(Long companyNo);
    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);
    List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO);

}

