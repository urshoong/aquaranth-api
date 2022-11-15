package com.dq.aquaranth.roleGroup.mapper;

import com.dq.aquaranth.roleGroup.domain.MenuRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MenuRoleMapperTest {
    @Autowired(required = false)
    private MenuRoleMapper menuRoleMapper;

    @Test
    @DisplayName("권한그룹이 가지고있는 메뉴권한 목록을 가지고 옵니다.")
    void findAll() {
        Long roleGroupNo = 12L;
        log.info(menuRoleMapper.findAllByRoleGroupNo(roleGroupNo));
    }

    @Test
    @DisplayName("권한그룹에 메뉴권한들을 추가합니다.")
    @Rollback
    void insert() {
        // given
        List<Long> menuNoList = List.of(3L, 4L);
        Long roleGroupNo = 12L;
        String regUser = "테스트종현";

        for (long menuNo : menuNoList) {
            MenuRole insertMenuRole = MenuRole.builder()
                    .menuNo(menuNo)
                    .roleGroupNo(roleGroupNo)
                    .regUser(regUser)
                    .regDate(LocalDateTime.now())
                    .build();

//            when
            menuRoleMapper.insert(insertMenuRole);
            log.info("추가된 메뉴권한 번호" + insertMenuRole.getMenuNo());

//            then
        }
    }

    @Test
    @DisplayName("권한그룹번호로 모든 메뉴권한을 삭제합니다.")
    void deleteById() {
        // given
        Long deleteRoleGroupNo = 12L;

        // when
        menuRoleMapper.deleteAllByRoleGroupNo(deleteRoleGroupNo);

        // then
        assertEquals(0, menuRoleMapper.findAllByRoleGroupNo(deleteRoleGroupNo).size());
    }
}
