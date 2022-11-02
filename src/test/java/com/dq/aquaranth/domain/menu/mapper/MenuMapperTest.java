package com.dq.aquaranth.domain.menu.mapper;

import com.dq.aquaranth.menu.dto.response.MenuResponse;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;


@Log4j2
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuMapperTest {

    @Autowired
    MenuMapper menuMapper;

    @Test
    void findAllMenus() {
        List<MenuResponse> menuResponses = menuMapper.findAllMenus();
        menuResponses.forEach(log::info);
    }

    @Test
    void findByGnBMenus() {
        List<MenuResponse> menuResponses = menuMapper.findByGnBMenus();
        menuResponses.forEach(log::info);
    }
}
