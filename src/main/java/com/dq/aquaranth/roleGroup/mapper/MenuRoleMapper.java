package com.dq.aquaranth.roleGroup.mapper;

import com.dq.aquaranth.roleGroup.domain.MenuRole;
import com.dq.aquaranth.roleGroup.dto.MenuRoleLnbDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper {

    List<MenuRole> findAllByRoleGroupNo(Long roleGroupNo);

    List<MenuRoleLnbDTO> findByRoleGroupNoAndModuleCode(@Param("roleGroupNo") Long roleGroupNo, @Param("moduleCode") String moduleCode);

    void insert(MenuRole menuRole);

    void deleteAllByRoleGroupNo(Long roleGroupNo);

    void deleteByRoleGroupNoAndModuleCode(@Param("roleGroupNo") Long roleGroupNo, @Param("moduleCode") String moduleCode);
}
