package com.dq.aquaranth.userRole.service;

import com.dq.aquaranth.userRole.dto.*;

import java.util.List;

public interface UserRoleService {

    List<UserRoleCompanyDTO> findCompany(Long orgaNo);

    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);

    List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO);

    Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO);

    Integer removeUserRole(UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO);

    List<UserRoleUserListBasedDTO> findUserListByOrgaNo(UserRoleReqUserListBasedDTO userRoleReqUserListBasedDTO);

    List<UserRoleUserBasedRoleGroupDTO> findByRoleGroupByUser(Long orgaNo);
}
