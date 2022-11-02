package com.dq.aquaranth.roleGroup.mapper;

import com.dq.aquaranth.roleGroup.dto.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleGroupMapper {

    /**
     * 권한그룹들을 조회합니다.
     */
    @Select("select * from role_group")
    List<RoleGroupDTO> findAll();

    /**
     * 권한그룹번호로 권한그룹들을 조회합니다.
     *
     * @param roleGroupNo : 찾으려는 권한그룹번호입니다.
     * @return 찾은 권한그룹을 전부 반환합니다.
     */
    @Select("select * from role_group where role_group_no = #{roleGroupNo}")
    RoleGroupDTO findById(Long roleGroupNo);

    /**
     * 권한그룹 등록
     *
     * @param insertDTO : 등록할 권한그룹 객체
     */
    @Insert("insert into role_group (role_group_name, role_group_use, company_name) values (#{roleGroupName}, #{roleGroupUse}, #{companyName})")
    @Options(useGeneratedKeys = true, keyProperty = "roleGroupNo")
    Long insert(RoleGroupDTO insertDTO);

    /**
     * 권한그룹번호로 권한그룹을 수정합니다.
     * @param updateDTO : 수정할 권한그룹 객체
     */
    @Update("update role_group " +
            "set role_group_name = #{roleGroupName} ," +
            "role_group_use = #{roleGroupUse} " +
            "where role_group_no = #{roleGroupNo}")
    void update(RoleGroupUpdateDTO updateDTO);

    /**
     * 권한그룹번호로 권한그룹명 수정하기
     * @param updateDTO : 수정할 권한그룹 객체 (권한그룹번호, 권한그룹명)
     */
    @Update("update role_group " +
            "set role_group_name = #{roleGroupName} " +
            "where role_group_no = #{roleGroupNo}")
    void updateRoleGroupName(RoleGroupNameUpdateDTO updateDTO);

    /**
     * 권한그룹번호로 권한그룹 사용여부 수정하기
     *
     * @param updateDTO : 수정할 권한그룹 객체 (권한그룹번호, 권한그룹사용여부)
     */
    @Update("update role_group " +
            "set role_group_use = #{roleGroupUse} " +
            "where role_group_no = #{roleGroupNo}")
    void updateRoleGroupUse(RoleGroupUseUpdateDTO updateDTO);

    /**
     * 권한그룹번호로 권한그룹을 삭제합니다.
     * @param roleGroupNo : 삭제할 권한그룹번호
     */
    @Delete("delete from role_group where role_group_no = #{roleGroupNo}")
    void deleteById(Long roleGroupNo);
}
