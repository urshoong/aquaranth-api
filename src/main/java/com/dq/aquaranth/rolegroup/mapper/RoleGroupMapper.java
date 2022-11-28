package com.dq.aquaranth.rolegroup.mapper;

import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.dq.aquaranth.rolegroup.dto.RoleGroupResponseDTO;
import com.dq.aquaranth.rolegroup.dto.RoleGroupUpdateDTO;

import java.util.List;

/**
 * 권한그룹 DB 접근을 위한 Mapper.
 *
 * @author 임종현
 */
public interface RoleGroupMapper {

    /**
     * 권한그룹들을 조회합니다.
     */
    List<RoleGroupResponseDTO> findAll();

    /**
     * 권한그룹번호로 권한그룹들을 조회합니다.
     *
     * @param roleGroupNo : 찾으려는 권한그룹번호입니다.
     * @return 찾은 권한그룹을 전부 반환합니다.
     */
    RoleGroup findById(Long roleGroupNo);

    /**
     * 권한그룹 등록
     *
     * @param roleGroup : 등록할 권한그룹 객체
     */
    Long insert(RoleGroup roleGroup);

    /**
     * 권한그룹번호로 권한그룹을 수정합니다.
     * @param updateDTO : 수정할 권한그룹 객체
     */
    void update(RoleGroupUpdateDTO updateDTO);

    /**
     * 권한그룹번호로 권한그룹을 삭제합니다.
     * @param roleGroupNo : 삭제할 권한그룹번호
     */
    void deleteById(Long roleGroupNo);

    void hideById(Long roleGroupNo);

    //33
    List<RoleGroup> findRoleGroupsByLoginUser(LoginUser loginUser);

    List<RoleGroup> findByMenuCode(String menuCode);
}
