package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dq.aquaranth.menu.constant.MenuCode.BOARD;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Log4j2
class DefaultMenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    @DisplayName(value = "전체 메뉴를 조회")
    void findAll() {
        List<MenuResponseDTO> menuResponseDTOList = menuService.findAll();
        menuResponseDTOList.forEach(log::info);
        assertThat(menuResponseDTOList).isNotNull();
    }

    @Test
    @DisplayName(value = "menuNo 값을 이용한 특정 메뉴를 조회")
    void findByMenuNo() {
        Long menuNo = 5L;
        Optional<MenuResponseDTO> menuResponseDTO = menuService.findByMenuNo(menuNo);
        log.info(menuResponseDTO);
        assertThat(menuResponseDTO).isPresent();
    }

    @Test
    @DisplayName(value = "menuCode 값을 이용한 특정 메뉴를 조회")
    void findByMenuCode() {
        String menuCode = BOARD.getCode();
        Optional<MenuResponseDTO> menuResponseDTO = menuService.findByMenuCode(menuCode);
        log.info(menuResponseDTO);
        assertThat(menuResponseDTO).isPresent();
    }

    @Test
    @DisplayName(value = "메뉴 상태 업데이트")
    void update() {
        Long menuNo = 12L;
        String menuName = "구글 드라이브";
        MenuUpdateDTO menuUpdateDTO = MenuUpdateDTO.builder()
                .menuNo(menuNo)
                .menuName(menuName)
                .menuUse(false)
                .menuOrder(99L)
                .build();
        menuService.update(menuUpdateDTO);
        Optional<MenuResponseDTO> expectedMenuResponseDto = menuService.findByMenuNo(menuNo);
        assertThat(Objects.requireNonNull(expectedMenuResponseDto.orElseGet(() -> null)).getMenuName()).isEqualTo(menuName);
    }
}
