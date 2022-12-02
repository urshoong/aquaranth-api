package com.dq.aquaranth.menu.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Log4j2
@SpringBootTest
class DefaultMenuServiceTestCodes {

    @Autowired
    MenuService menuService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName(value = "전체 메뉴를 조회")
    void findAll() {
//        List<MenuResponseDTO> menuResponseDTOList = menuService.findBy();
//        menuResponseDTOList.forEach(log::info);
//        assertThat(menuResponseDTOList).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName(value = "메뉴 상태 업데이트")
    void update() {
//        Long menuNo = 12L;
//        String menuName = "구글 드라이브";
//        MenuUpdateDTO menuUpdateDTO = MenuUpdateDTO.builder()
//                .menuNo(menuNo)
//                .menuName(menuName)
//                .mainFlag(false)
//                .menuOrder(99L)
//                .build();
//        Optional<MenuResponseDTO> expectedMenuResponseDto = Optional.ofNullable(menuService.update(menuUpdateDTO));
//        assertThat(Objects.requireNonNull(expectedMenuResponseDto.orElseGet(() -> null)).getMenuName()).isEqualTo(menuName);
    }

    @Test
    @Transactional
    @DisplayName(value = "메뉴 상태 업데이트")
    void findAllByInRedis() {

//        Long menuNo = 12L;
//        String menuName = "구글 드라이브";
//        MenuUpdateDTO menuUpdateDTO = MenuUpdateDTO.builder()
//                .menuNo(menuNo)
//                .menuName(menuName)
//                .mainFlag(false)
//                .menuOrder(99L)
//                .build();
//        Optional<MenuResponseDTO> expectedMenuResponseDto = Optional.ofNullable(menuService.update(menuUpdateDTO));
//        assertThat(Objects.requireNonNull(expectedMenuResponseDto.orElseGet(() -> null)).getMenuName()).isEqualTo(menuName);
    }
}
