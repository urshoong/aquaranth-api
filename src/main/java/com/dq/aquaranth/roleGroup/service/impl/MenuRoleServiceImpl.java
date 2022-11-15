package com.dq.aquaranth.roleGroup.service.impl;

import com.dq.aquaranth.roleGroup.domain.MenuRole;
import com.dq.aquaranth.roleGroup.mapper.MenuRoleMapper;
import com.dq.aquaranth.roleGroup.service.MenuRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuRoleServiceImpl implements MenuRoleService {
    private final MenuRoleMapper menuRoleMapper;

    @Override
    public List<MenuRole> insert(List<MenuRole> insetMenuRoles) {
        List<MenuRole> result = new ArrayList<>();

        for (MenuRole menuRole : insetMenuRoles) {
            log.info(menuRole.getRoleGroupNo() + "번 권한그룹에 {}번 메뉴권한을 추가합니다.", menuRole.getMenuNo());
            menuRoleMapper.insert(menuRole);
        }

        return result;
    }

    @Override
    public void deleteByRoleGroupNo(Long roleGroupNo) {
        menuRoleMapper.deleteAllByRoleGroupNo(roleGroupNo);
    }

    @Override
    @Transactional
    public void save(List<MenuRole> insertMenuRoles, String moduleCode) {
        if (insertMenuRoles.size() == 0) {
            log.error("추가할 메뉴가 존재하지 않습니다");
            return;
        }

        Long roleGroupNo = insertMenuRoles.get(0).getRoleGroupNo();
        menuRoleMapper.deleteByRoleGroupNoAndModuleCode(roleGroupNo, moduleCode);

        for (MenuRole menuRole : insertMenuRoles) {
            menuRoleMapper.insert(menuRole);
        }

        log.info("{}번 권한그룹에 메뉴권한들이 정상적으로 저장되었습니다.", roleGroupNo);
    }
}
