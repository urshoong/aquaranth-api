package com.dq.aquaranth.userRole.mapper;

import com.dq.aquaranth.userRole.dto.UserRoleCompanyDTO;
import com.dq.aquaranth.userRole.dto.UserRoleReqRoleGroupBasedListDTO;
import com.dq.aquaranth.userRole.dto.UserRoleRoleGroupBasedListDTO;

import java.util.List;

public interface UserRoleMapper {
    List<UserRoleCompanyDTO> findCompanyAll();
    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyNameAndSearch(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);
}
