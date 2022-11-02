package com.dq.aqaranth.domain.menu.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


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
