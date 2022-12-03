package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryUserDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuTreeResponseDTO;
import com.dq.aquaranth.menu.exception.CommonException;
import com.dq.aquaranth.menu.exception.ErrorCodes;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Log4j2
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuMapperTest {
    static List<Long> mockRoleGroups;
    @Autowired
    MenuMapper menuMapper;

    @BeforeEach
    @DisplayName("권한그룹 생성")
    void setUp() {
        mockRoleGroups = new ArrayList<>();
        LongStream.range(1L,5L).forEach(value -> mockRoleGroups.add(value));
    }

    @Test
    @DisplayName(value = "1번 권한그룹부터 5번 권한그룹까지 조회할 수 있는 리스트")
    void findBy() {
        //given
        MenuQueryDTO menuQueryDTO = MenuQueryDTO.builder().menuCode("ORGA0030").build();

        //when
        List<MenuTreeResponseDTO> expectedMenuList = menuMapper.findAllBy(menuQueryDTO, mockRoleGroups);

        //then
        log.info(expectedMenuList);
//        assertThat(expectedMenuList).isNotNull();
    }

    @Test
    @DisplayName(value = "admin 계정이 접근할 수 있는 메뉴 리스트를 조회")
    void findAllBy() {
        //given
        MenuQueryDTO menuQueryDTO = MenuQueryDTO.builder()
                .menuCode("SYS")
                .keyword("lnb").build();

        //when
        List<MenuTreeResponseDTO> expectedMenu = menuMapper.findAllBy(menuQueryDTO, mockRoleGroups);

        //then
        expectedMenu.forEach(log::info);
        assertThat(expectedMenu).isNotNull();
    }
}
