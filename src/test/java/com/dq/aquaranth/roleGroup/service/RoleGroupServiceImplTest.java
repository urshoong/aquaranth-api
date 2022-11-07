package com.dq.aquaranth.roleGroup.service;

import com.dq.aquaranth.roleGroup.dto.RoleGroupDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupModifyReqDTO;
import com.dq.aquaranth.roleGroup.dto.RoleGroupUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class RoleGroupServiceImplTest {

    @Autowired
    RoleGroupService roleGroupService;

    @Test
    @DisplayName("권한그룹을 삭제할때, 없는번호라면 예외를 반환하나요?")
    void delete_fail_throwException() {
        // given
        Long deleteNo = 6L; // 존재하지 않는 권한그룹번호
        RoleGroupDTO findDTO = roleGroupService.findById(deleteNo);

        // when & then
        assertThrows(RuntimeException.class, () -> roleGroupService.delete(deleteNo));
    }

    @Test
    @DisplayName("권한그룹을 삭제가 정상적으로 이루어지나요?")
    void delete_success() {
        // given
        Long deleteNo = 5L; // 존재하는 권한그룹번호
        RoleGroupDTO findDTO = roleGroupService.findById(deleteNo);
        log.info("삭제할 권한그룹 {}", findDTO);

        // when
        roleGroupService.delete(deleteNo);

        // then
        assertNull(roleGroupService.findById(findDTO.getRoleGroupNo()));
    }

    @Test
    @DisplayName("권한그룹 삭제시에 메뉴권한테이블의 데이터도 함께 삭제되지 않으면 롤백처리 됩니다")
    void del_roleGroup_with_menuRole() {
        // given


    }
}
