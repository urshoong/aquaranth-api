package com.dq.aquaranth.userrole.mapper;

import com.dq.aquaranth.userrole.dto.*;

import java.util.List;

public interface UserRoleMapper {

    List<UserRoleCompanyDTO> findCompany(Long orgaNo);

    List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO);

    List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO);

    Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO);

    Integer removeUserRole(UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO);

    List<UserRoleUserListBasedDTO> findUserListByOrgaNo(UserRoleReqUserListBasedDTO userRoleUserReqListBasedDTO);

    List<UserRoleUserBasedRoleGroupDTO> findByRoleGroupByUser(Long orgaNo);
}

