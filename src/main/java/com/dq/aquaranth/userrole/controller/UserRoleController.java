package com.dq.aquaranth.userrole.controller;

import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.Menu;
import com.dq.aquaranth.userrole.dto.paging.PageRequestDTO;
import com.dq.aquaranth.userrole.dto.paging.PageResponseDTO;
import com.dq.aquaranth.userrole.dto.request.UserRoleReqInsertOrgaRoleDTO;
import com.dq.aquaranth.userrole.dto.request.UserRoleReqRemoveOrgaRoleDTO;
import com.dq.aquaranth.userrole.dto.request.UserRoleReqRemoveUserRoleDTO;
import com.dq.aquaranth.userrole.dto.response.*;
import com.dq.aquaranth.userrole.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/userrole")
@MenuCode(Menu.ROLE0010)
public class UserRoleController {

    private final UserRoleService userRoleService;

    /** GroupList **/

    // TODO : 차후에 안쓰면 삭제처리
    @Operation(summary = "모든 회사 정보 조회", description = "사용 가능한 모든 회사 정보를 조회합니다.")
    @GetMapping("/companyListAll")
    public List<UserRoleCompanyDTO> findCompanyAll() {
        return userRoleService.findCompanyAll();
    }

    @Operation(summary = "회사 정보 조회", description = "로그인한 사용자의 계정 정보를 사용하여 회사 정보를 조회합니다.")
    @GetMapping("/companyList")
    public List<UserRoleCompanyDTO> findCompany(Authentication authentication){
        return userRoleService.findCompany(authentication.getName());
    }
    @Operation(summary = "권한 그룹 목록 조회", description = "선택한 회사에 부여 가능한 모든 권한그룹의 목록을 조회합니다.")
    @GetMapping("/roleGroupList")
    public PageResponseDTO<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyNo(PageRequestDTO pageRequestDTO) {
        return userRoleService.findRoleGroupByOrgaNo(pageRequestDTO);
    }

    @Operation(summary = "조직(회사/부서/사원) 목록 조회", description = "선택한 권한그룹이 부여된 조직(회사/부서/사원) 목록을 조회합니다.")
    @GetMapping("/roleGroupUserList")
    public PageResponseDTO<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(PageRequestDTO pageRequestDTO) {
        return userRoleService.findOrgaByRoleGroupNo(pageRequestDTO);
    }

    @Operation(summary = "권한그룹 부여", description = "공통 조직도에서 선택한 조직(회사/부서/사원)에 선택한 권한그룹을 부여합니다.")
    @PostMapping("/insertOrgaRole")
    public Integer insertOrgaRole(@RequestBody UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO, Authentication authentication) {
        userRoleReqInsertOrgaRoleDTO.setUsername(authentication.getName());
        return userRoleService.insertUserRole(userRoleReqInsertOrgaRoleDTO);
    }

    @Operation(summary = "부여된 권한그룹 제거", description = "선택한 조직(회사/부서/사원)에 부여된 권한그룹을 제거합니다.")
    @PostMapping("/removeOrgaRole")
    public Integer removeOrgaRole(@RequestBody UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO) {
        log.info("removeOrgaRole");
        log.info(userRoleReqRemoveOrgaRoleDTO);
        return userRoleService.removeOrgaRole(userRoleReqRemoveOrgaRoleDTO);
    }

    /** UserList **/

    @Operation(summary = "사용자 목록 조회", description = "선택한 회사에 소속된 모든 사용자의 목록을 조회합니다.")
    @GetMapping("/findUserListByOrgaNo")
    public PageResponseDTO<UserRoleUserListBasedDTO> findUserListByOrgaNo(PageRequestDTO pageRequestDTO){
        return userRoleService.findUserListByOrgaNo(pageRequestDTO);
    }

    @Operation(summary = "권한그룹 목록 조회", description = "선택한 사용자에 부여된 모든 권한그룹을 조회합니다.")
    @GetMapping("/findRoleGroupByUser")
    public PageResponseDTO<UserRoleUserBasedRoleGroupDTO> findRoleGroupByUser(PageRequestDTO pageRequestDTO) {
        return userRoleService.findRoleGroupByUser(pageRequestDTO);
    }

    @Operation(summary = "부여된 권한그룹 제거", description = "선택한 사용자에 부여된 권한그룹 중 본인에게 부여된 권한그룹만 제거 가능합니다.")
    @PostMapping("/removeOrgaRoleByAllRole")
    public UserRoleResponseDTO removeUserRole(@RequestBody List<UserRoleReqRemoveUserRoleDTO> removeData){
        return userRoleService.removeOrgaRole(removeData);
    }
}
