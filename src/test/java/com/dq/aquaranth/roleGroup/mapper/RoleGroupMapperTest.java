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
        String newRoleGroupName = "test 권한그룹3";
        boolean newRoleGroupUse = true;
        String newCompanyName = "KAKAO";

        RoleGroupDTO insertDTO = RoleGroupDTO.builder()
                .roleGroupName(newRoleGroupName)
                .roleGroupUse(newRoleGroupUse)
                .companyName(newCompanyName)
                .build();

        // when
        roleGroupMapper.insert(insertDTO); // 추가된 권한그룹번호

        // then
        assertNotNull(insertDTO.getRoleGroupNo());
        log.info(insertDTO);
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹명 수정하기")
    void update() {
        // given
        Long updateRoleGroupNo = 2L;
        String updateRoleGroupName = "update 권한그룹명1"; // 바꿀 권한그룹명
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
        // 권한 그룹 수정이 일어나지 않았다면, updateRoleGroupNo에 대한 권한그룹명은 그대로일 것이다.
        RoleGroupDTO afterDTO = roleGroupMapper.findById(updateRoleGroupNo);
        assertEquals(afterDTO.getRoleGroupName(), updateRoleGroupName);
        assertNotEquals(beforeDTO.isRoleGroupUse(), afterDTO.isRoleGroupUse());
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹명 수정하기")
    void updateRoleGroupName() {
        // given
        Long updateRoleGroupNo = 2L;
        String updateRoleGroupName = "update 권한그룹명1"; // 바꿀 권한그룹명

        RoleGroupNameUpdateDTO updateDTO = RoleGroupNameUpdateDTO.builder()
                .roleGroupNo(updateRoleGroupNo)
                .roleGroupName(updateRoleGroupName)
                .build();

        // when
        roleGroupMapper.updateRoleGroupName(updateDTO);

        // then
        // 권한 그룹 수정이 일어나지 않았다면, updateRoleGroupNo에 대한 권한그룹명은 그대로일 것이다.
        RoleGroupDTO findDTO = roleGroupMapper.findById(updateRoleGroupNo);
        assertEquals(findDTO.getRoleGroupName(), updateRoleGroupName);
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹 사용여부 수정하기")
    void updateRoleGroupUse() {
        // given
        Long updateRoleGroupNo = 2L;
        RoleGroupDTO beforeDTO = roleGroupMapper.findById(updateRoleGroupNo); // 수정전  권한그룹
        boolean updateRoleGroupUse = !beforeDTO.isRoleGroupUse(); // 기존 사용여부의 반대로

        RoleGroupUseUpdateDTO updateDTO = RoleGroupUseUpdateDTO.builder()
                .roleGroupNo(updateRoleGroupNo)
                .roleGroupUse(updateRoleGroupUse)
                .build();

        // when
        roleGroupMapper.updateRoleGroupUse(updateDTO);

        // then
        // 기존 사용여부의 반대로 사용여부를 수정했기 때문에,
        // 권한그룹 수정이 정상적으로 일어났다면, 사용여부는 서로 달라야 할 것이다.
        RoleGroupDTO afterDTO = roleGroupMapper.findById(updateRoleGroupNo);
        assertNotEquals(beforeDTO.isRoleGroupUse(), afterDTO.isRoleGroupUse());
    }

    @Test
    @DisplayName("권한그룹번호로 권한그룹을 삭제합니다.")
    void deleteById() {
        // given
        Long deleteRoleGroupNo = 1L;

        // when
        roleGroupMapper.deleteById(deleteRoleGroupNo);

        // then
        assertNull(roleGroupMapper.findById(deleteRoleGroupNo));
    }

}
