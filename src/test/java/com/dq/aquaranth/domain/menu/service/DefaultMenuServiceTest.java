package com.dq.aquaranth.domain.menu.service;

import com.dq.aquaranth.menu.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Log4j2
@SpringBootTest
class DefaultMenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void findAllMenus() {
        menuService.findAllMenus().forEach(log::info);
    }
}
