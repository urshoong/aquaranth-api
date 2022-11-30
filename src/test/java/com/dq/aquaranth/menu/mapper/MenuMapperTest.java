package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.UserDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuMapperTest {

    static UserDTO userDTO;
    @Autowired
    MenuMapper menuMapper;

    @BeforeEach
    void setUp() {
        String username = "admin";
        Long companyNo = 3L;
        Long deptNo = 3L;
        userDTO = UserDTO.builder().companyNo(companyNo).deptNo(deptNo).username(username).build();
    }

    @Test
    @DisplayName(value = "admin 계정이 접근할 수 있는 메뉴를 조회")
    void findBy() {
        //given
        MenuRequestDTO menuRequestDTO = MenuRequestDTO.builder().menuCode("ORGA0030").build();

        //when
        MenuResponseDTO expectedMenu = menuMapper.findBy(menuRequestDTO, userDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));

        //then
        log.info(menuRequestDTO);
        assertThat(expectedMenu).isNotNull();
    }

    @Test
    @DisplayName(value = "admin 계정이 접근할 수 있는 메뉴 리스트를 조회")
    void findAllBy() {
        //given
        MenuRequestDTO menuRequestDTO = MenuRequestDTO.builder()
                .menuCode("SYS")
                .keyword("lnb").build();

        //when
        List<MenuResponseDTO> expectedMenu = menuMapper.findAllBy(menuRequestDTO, userDTO);

        //then
        expectedMenu.forEach(log::info);
        assertThat(expectedMenu).isNotNull();
    }
}
