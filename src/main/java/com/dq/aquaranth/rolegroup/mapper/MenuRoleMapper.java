package com.dq.aquaranth.rolegroup.mapper;

import com.dq.aquaranth.rolegroup.domain.MenuRole;
import com.dq.aquaranth.rolegroup.dto.MenuRoleLnbDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 권한그룹 DB 접근을 위한 Mapper.
 *
 * @author 임종현
 */
public interface MenuRoleMapper {

    List<MenuRole> findAllByRoleGroupNo(Long roleGroupNo);

    List<MenuRoleLnbDTO> findByRoleGroupNoAndModuleCode(@Param("roleGroupNo") Long roleGroupNo, @Param("moduleCode") String moduleCode);

    void insert(MenuRole menuRole);

    Integer deleteAllByRoleGroupNo(Long roleGroupNo);

    void deleteByRoleGroupNoAndModuleCode(@Param("roleGroupNo") Long roleGroupNo, @Param("moduleCode") String moduleCode);

}
