package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.domain.MenuRole;

import java.util.List;

public interface MenuRoleService {
    List<MenuRole> insert(List<MenuRole> insertMenuRoles);

    void deleteByRoleGroupNo(Long roleGroupNo);

    void save(List<MenuRole> insertMenuRoles, String moduleCode);
}
