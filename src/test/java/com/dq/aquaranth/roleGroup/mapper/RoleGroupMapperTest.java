package com.dq.aquaranth.roleGroup.mapper;

import com.dq.aquaranth.roleGroup.dto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class RoleGroupMapperTest {
    @Autowired(required = false)
    private RoleGroupMapper roleGroupMapper;

    @Test
    @DisplayName("권한그룹 목록을 가져옵니다.")
    void findAll() {
        log.info(roleGroupMapper.findAll());
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹을 조회합니다.")
    void findById() {
        Long roleGroupNo = 1L;
        log.info(roleGroupMapper.findById(roleGroupNo));
    }

    @Test
    @DisplayName("권한그룹을 추가합니다.")
    void insert() {
        // given
        String newRoleGroupName = "test 권한그룹666";
        boolean newRoleGroupUse = true;
        String newCompanyName = "KAKAO";

        RoleGroupDTO insertDTO = RoleGroupDTO.builder()
                .roleGroupName(newRoleGroupName)
                .roleGroupUse(newRoleGroupUse)
                .companyName(newCompanyName)
                .build();

        // when
        roleGroupMapper.insert(insertDTO); // 권한그룹 추가하기

        // then
        assertNotNull(insertDTO.getRoleGroupNo());
        log.info(insertDTO);
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹명 수정하기")
    void update() {
        // given
        Long updateRoleGroupNo = 6L;
        String updateRoleGroupName = "update222 권한그룹명66666"; // 바꿀 권한그룹명
        RoleGroupDTO beforeDTO = roleGroupMapper.findById(updateRoleGroupNo); // 수정전  권한그룹
        boolean updateRoleGroupUse = !beforeDTO.isRoleGroupUse(); // 기존 사용여부의 반대로

        RoleGroupUpdateDTO updateDTO = RoleGroupUpdateDTO.builder()
                .roleGroupNo(updateRoleGroupNo)
                .roleGroupName(updateRoleGroupName)
                .roleGroupUse(updateRoleGroupUse)
                .build();

        // when
        roleGroupMapper.update(updateDTO);

        // then
        RoleGroupDTO afterDTO = roleGroupMapper.findById(updateRoleGroupNo);
        // 권한 그룹 수정이 일어나지 않았다면, updateRoleGroupNo에 대한 권한그룹명은 그대로일 것이다.
        assertEquals(afterDTO.getRoleGroupName(), updateRoleGroupName);
        // 사용여부를 기존 권한그룹 사용여부의 반대값으로 수정하였기 때문에 사용여부는 서로 달라야 할 것이다.
        assertNotEquals(beforeDTO.isRoleGroupUse(), afterDTO.isRoleGroupUse());

        log.info("수정 후 권한그룹 => {}", afterDTO);
    }


    @Test
    @DisplayName("권한그룹번호로 권한그룹을 삭제합니다.")
    void deleteById() {
        // given
        Long deleteRoleGroupNo = 2L;

        // when
        roleGroupMapper.deleteById(deleteRoleGroupNo);

        // then
        assertNull(roleGroupMapper.findById(deleteRoleGroupNo));
    }

}
