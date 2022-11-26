package com.dq.aquaranth.userrole.service;

import com.dq.aquaranth.userrole.dto.paging.PageRequestDTO;
import com.dq.aquaranth.userrole.dto.paging.PageResponseDTO;
import com.dq.aquaranth.userrole.dto.request.*;
import com.dq.aquaranth.userrole.dto.response.*;
import com.dq.aquaranth.userrole.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

    /** GroupList **/

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleCompanyDTO> findCompanyByOrgaNo(Long orgaNo) {
        return userRoleMapper.findCompanyByOrgaNo(orgaNo);
    }

    @Override
    public List<UserRoleCompanyDTO> findCompany(String username) {
        return userRoleMapper.findCompany(username);
    }

    public PageResponseDTO findRoleGroupByOrgaNo(PageRequestDTO pageRequestDTO) {
        List<UserRoleRoleGroupBasedListDTO> list = userRoleMapper.findRoleGroupByOrgaNo(pageRequestDTO);

        Integer total = userRoleMapper.findRoleGroupTotalByOrgaNo(pageRequestDTO);
        total = total == null ? 0 : total;

        return new PageResponseDTO(pageRequestDTO, total, list);
    }

    @Override
    public PageResponseDTO findOrgaByRoleGroupNo(PageRequestDTO pageRequestDTO) {
        List<UserRoleGroupBasedUserListDTO> list = userRoleMapper.findOrgaByRoleGroupNo(pageRequestDTO);

        Integer total = userRoleMapper.findOrgaTotalByRoleGroupNo(pageRequestDTO);
        total = total == null ? 0 : total;

        return new PageResponseDTO(pageRequestDTO, total, list);
    }

    @Override
    public Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO) {
        return userRoleMapper.insertUserRole(userRoleReqInsertOrgaRoleDTO);
    }

    @Override
    public Integer removeOrgaRole(UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO) {
        return userRoleMapper.removeOrgaRole(userRoleReqRemoveOrgaRoleDTO);
    }

    /** UserList **/

    @Override
    @Transactional
    public PageResponseDTO findUserListByOrgaNo(PageRequestDTO pageRequestDTO) {
        List<UserRoleUserListBasedDTO> list = userRoleMapper.findUserListByOrgaNo(pageRequestDTO);

        Integer total = userRoleMapper.findUserListTotalByOrgaNo(pageRequestDTO);
        total = total == null ? 0 : total;

        return new PageResponseDTO(pageRequestDTO, total, list);
    }

    @Override
    public PageResponseDTO findRoleGroupByUser(PageRequestDTO pageRequestDTO) {
        List<UserRoleUserBasedRoleGroupDTO> list = userRoleMapper.findRoleGroupByUser(pageRequestDTO);

        Integer total = userRoleMapper.findRoleGroupTotalByUser(pageRequestDTO);
        total = total == null ? 0 : total;

        return new PageResponseDTO(pageRequestDTO, total, list);
    }

    @Override
    public UserRoleResponseDTO removeOrgaRole(List<UserRoleReqRemoveUserRoleDTO> removeData) {
        UserRoleResponseDTO result = UserRoleResponseDTO.builder().build();
        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicReference<Long> targetOrgaNo = new AtomicReference<>((long) 0);

        removeData.forEach(dto -> {
            targetOrgaNo.set(dto.getTargetOrgaNo());
            if(!Objects.equals(dto.getOrgaNo(), dto.getTargetOrgaNo())) flag.set(true);
        });

        if(flag.get()){
            result.setState("fail");
            result.setMessage("다른 회사/부서/사원에 부여된 권한은 해제할 수 없습니다.");
        }else{
            List<Long> removeUserRoleList = removeData.stream()
                    .filter(dto -> Objects.equals(dto.getTargetOrgaNo(), dto.getOrgaNo()))
                    .map(UserRoleReqRemoveUserRoleDTO::getRoleGroupNo)
                    .collect(Collectors.toList());

            Integer beforeSize = removeUserRoleList.size();

            UserRoleReqRemoveUserRoleDTO2 userRoleReqRemoveUserRoleDTO2 = UserRoleReqRemoveUserRoleDTO2.builder()
                    .targetOrgaNo(targetOrgaNo.get())
                    .removeUserRoleList(removeUserRoleList)
                    .build();

            Integer afterSize = userRoleMapper.removeUserRole(userRoleReqRemoveUserRoleDTO2);

            log.info("beforeSize : " + beforeSize);
            log.info("afterSize : " + afterSize);

            result.setState("success");
            result.setMessage("선택한 권한그룹이 해제되었습니다.");
        }

        return result;
    }
}
