package com.dq.aquaranth.userRole.mapper;

import com.dq.aquaranth.userRole.dto.UserRoleCompanyDTO;
import com.dq.aquaranth.userRole.dto.UserRoleReqRoleGroupBasedListDTO;
import com.dq.aquaranth.userRole.dto.UserRoleRoleGroupBasedListDTO;

import java.util.List;

public interface UserRoleMapper {
    List<UserRoleCompanyDTO> findCompany(Long companyNo);
    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);
}
