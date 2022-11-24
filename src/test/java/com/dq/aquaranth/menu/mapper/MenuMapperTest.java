package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dq.aquaranth.menu.constant.MenuCode.*;
import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuMapperTest {

    @Autowired
    MenuMapper menuMapper;

    @Test
    @DisplayName(value = "전체 메뉴를 조회")
    void findAll() {
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAll();
        menuResponseDTOList.forEach(log::info);
        assertThat(menuResponseDTOList).isNotNull();
    }

    @Test
    @DisplayName(value = "메뉴 상태 업데이트")
    void update() {
        Long menuNo = 12L;
        String menuName = "구글 드라이브";
        MenuUpdateDTO menuUpdateDTO = MenuUpdateDTO.builder().menuNo(menuNo).menuName(menuName).mainFlag(false).menuOrder(99L).build();
        menuMapper.update(menuUpdateDTO);
    }
    @Test
    @DisplayName(value = "메뉴 추가")
    void insert() {
        MenuInsertDTO menuInsertDTO = MenuInsertDTO.builder()
                .upperMenuNo(1L)
                .menuName("급여 관리")
                .mainFlag(true)
                .menuCode("ORGA0040")
                .menuPath("/SYS/ORGA/ORGA0040")
                .menuOrder(99L)
                .uuid("")
                .filename("")
                .regUser("admin")
                .build();
        menuMapper.insert(menuInsertDTO);
        log.info(menuInsertDTO);
    }

// TODO : MyBaits OGNL을 이용한 동적 쿼리 작성 # 25
    @Test
    void findBy() {
        String menuCode = ROLE0020.getCode();
        Long menuNo = 10L;
    }

//    @Test
//    void findMenusByLoginUsername() {
//        String username = "admin";
//        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findByMenuCodeUnderRecursive(username);
//        log.info(menuResponseDTOList);
//    }
}
