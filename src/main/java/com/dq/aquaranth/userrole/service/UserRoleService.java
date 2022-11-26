package com.dq.aquaranth.userrole.service;

import com.dq.aquaranth.userrole.dto.paging.PageRequestDTO;
import com.dq.aquaranth.userrole.dto.paging.PageResponseDTO;
import com.dq.aquaranth.userrole.dto.request.*;
import com.dq.aquaranth.userrole.dto.response.*;

import java.util.List;

public interface UserRoleService {

    /** GroupList **/

    List<UserRoleCompanyDTO> findCompanyByOrgaNo(Long orgaNo);

    List<UserRoleCompanyDTO> findCompany(String username);

    PageResponseDTO findRoleGroupByOrgaNo(PageRequestDTO pageRequestDTO);

    PageResponseDTO findOrgaByRoleGroupNo(PageRequestDTO pageRequestDTO);

    Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO);

    Integer removeOrgaRole(UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO);

    /** UserList **/

    PageResponseDTO findUserListByOrgaNo(PageRequestDTO pageRequestDTO);

    PageResponseDTO findRoleGroupByUser(PageRequestDTO pageRequestDTO);

    UserRoleResponseDTO removeOrgaRole(List<UserRoleReqRemoveUserRoleDTO> removeData);
}
