package com.dq.aqaranth.domain.menu.mapper;

import com.dq.aqaranth.domain.menu.dto.response.MenuResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
