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
    @DisplayName(value = "menuNo 값을 이용한 특정 메뉴를 조회")
    void findByMenuNo() {
        Long menuNo = 5L;
        Optional<MenuResponseDTO> menuResponseDTO = menuMapper.findByMenuNo(menuNo);
        log.info(menuResponseDTO);
        assertThat(menuResponseDTO).isPresent();
    }

    @Test
    @DisplayName(value = "menuCode 값을 이용한 특정 메뉴를 조회")
    void findByMenuCode() {
        String menuCode = BOARD.getCode();
        Optional<MenuResponseDTO> menuResponseDTO = menuMapper.findByMenuCode(menuCode);
        log.info(menuResponseDTO);
        assertThat(menuResponseDTO).isPresent();
    }

    @Test
    @DisplayName(value = "메뉴 상태 업데이트")
    void update() {
        Long menuNo = 12L;
        String menuName = "구글 드라이브";
        MenuUpdateDTO menuUpdateDTO = MenuUpdateDTO.builder().menuNo(menuNo).menuName(menuName).mainFlag(false).menuOrder(99L).build();
        menuMapper.update(menuUpdateDTO);
        Optional<MenuResponseDTO> expectedMenuResponseDto = menuMapper.findByMenuNo(menuNo);
        assertThat(Objects.requireNonNull(expectedMenuResponseDto.orElseGet(() -> null)).getMenuName()).isEqualTo(menuName);
    }

//    @Test
//    @DisplayName(value = "URL 정보를 포함한 모든 메뉴를 조회합니다.")
//    void findAllMenuInformation() {
//        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAllMenuInformation();
//        assertThat(menuResponseDTOList.get(1).getUrl()).isNotNull();
//    }

    @Test
    @DisplayName(value = "상위메뉴번호가 없는 메뉴(GNB)를 조회합니다.")
    void findByUpperMenuNoIsNull() {
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findByUpperMenuNoIsNull();
        assertThat(menuResponseDTOList.get(1).getUpperMenuNo()).isNull();
    }

    @Test
    @DisplayName(value = "하위 메뉴코드를 이용하여 상위메뉴번호가 null일때 까지 조회합니다.")
    void findByMenuCodeUpperRecursive() {
        String menuCode = ORGA0010.getCode();
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findByMenuCodeUpperRecursive(menuCode);
        assertThat(menuResponseDTOList).extracting("menuCode").contains(ORGA.getCode());
    }


    @Test
    @DisplayName(value = "상위 메뉴코드를 이용하여 모든 하위메뉴를 조회합니다.")
    void findByMenuCodeUnderRecursive() {
        String menuCode = SYS.getCode();
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findByMenuCodeUnderRecursive(menuCode);
        assertThat(menuResponseDTOList).extracting("menuCode").contains(ORGA0030.getCode());
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
//    @Test
//    void findBy() {
//        String menuCode = SYS.getCode();
//        Long menuNo = 14L;
//        Optional<MenuResponseDTO> menuResponseDTO = menuMapper.findBy(menuCode, menuNo);
//        Optional<MenuResponseDTO> menuResponseDTO1 = menuMapper.findBy(null, menuNo);
//        Optional<MenuResponseDTO> menuResponseDTO2 = menuMapper.findBy(menuCode, null);
//    }

//    @Test
//    void findMenusByLoginUsername() {
//        String username = "admin";
//        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findByMenuCodeUnderRecursive(username);
//        log.info(menuResponseDTOList);
//    }
}
