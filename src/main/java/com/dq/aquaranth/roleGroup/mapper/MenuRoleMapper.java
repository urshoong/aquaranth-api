package com.dq.aquaranth.roleGroup.mapper;

import com.dq.aquaranth.roleGroup.domain.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper {

    void insert(MenuRole menuRole);

    void deleteAllByRoleGroupNo(Long roleGroupNo);

    List<MenuRole> findAllByRoleGroupNo(Long roleGroupNo);

    void deleteByRoleGroupNoAndModuleCode(@Param("roleGroupNo") Long roleGroupNo, @Param("moduleCode") String moduleCode);
}
