package com.dq.aquaranth.roleGroup.service.impl;

import com.dq.aquaranth.roleGroup.domain.MenuRole;
import com.dq.aquaranth.roleGroup.service.MenuRoleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MenuRoleServiceImplTest {
    @Autowired
    private MenuRoleService menuRoleService;

    @Test
    @DisplayName("추가할 메뉴가 존재하지 않으면, 하위메뉴를 전부 초기화합니다.")
    void test_addMenu_notExist() {
        List<MenuRole> insertMenuRoles = new ArrayList<>();
        String moduleCode = "SYS";
        Long roleGroupNo = 12L;

        menuRoleService.save(insertMenuRoles, moduleCode, roleGroupNo);
    }

    @Test
    @DisplayName("삭제할 GNB의 menu code가 존재하지 않을 때")
    void test_moduleCode_notExist() {
        List<MenuRole> insertMenuRoles = new ArrayList<>();
        Long roleGroupNo = 12L;
        insertMenuRoles.add(MenuRole.builder()
                .menuNo(1L)
                .roleGroupNo(roleGroupNo)
                .regUser("테스트유저")
                .regDate(LocalDateTime.now()).build());

        String moduleCode = "SDFASDC";

        menuRoleService.save(insertMenuRoles, moduleCode, roleGroupNo);
    }


}
