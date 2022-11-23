package com.dq.aquaranth.rolegroup.service;

import com.dq.aquaranth.rolegroup.domain.MenuRole;
import com.dq.aquaranth.rolegroup.dto.MenuRoleLnbDTO;

import java.util.List;

public interface MenuRoleService {
    List<MenuRoleLnbDTO> findByRoleGroupNoAndModuleCode(Long roleGroupNo, String moduleCode);
    List<MenuRole> insert(List<MenuRole> insertMenuRoles);

    void deleteByRoleGroupNo(Long roleGroupNo);

    void save(List<MenuRole> insertMenuRoles, String moduleCode, Long roleGroupNo);
}
