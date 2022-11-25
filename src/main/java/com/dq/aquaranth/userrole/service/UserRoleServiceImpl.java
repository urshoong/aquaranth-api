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

        PageResponseDTO result = new PageResponseDTO(pageRequestDTO, total, list);

        return result;
    }

    @Override
    public PageResponseDTO findOrgaByRoleGroupNo(PageRequestDTO pageRequestDTO) {
        List<UserRoleGroupBasedUserListDTO> list = userRoleMapper.findOrgaByRoleGroupNo(pageRequestDTO);

        Integer total = userRoleMapper.findOrgaTotalByRoleGroupNo(pageRequestDTO);
        total = total == null ? 0 : total;

        PageResponseDTO result = new PageResponseDTO(pageRequestDTO, total, list);

        return result;
    }

    @Override
    public Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO, String username) {
        return userRoleMapper.insertUserRole(userRoleReqInsertOrgaRoleDTO, username);
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

        PageResponseDTO result = new PageResponseDTO(pageRequestDTO, total, list);

        return result;
    }

    @Override
    public PageResponseDTO findRoleGroupByUser(PageRequestDTO pageRequestDTO) {
        List<UserRoleUserBasedRoleGroupDTO> list = userRoleMapper.findRoleGroupByUser(pageRequestDTO);

        Integer total = userRoleMapper.findRoleGroupTotalByUser(pageRequestDTO);
        total = total == null ? 0 : total;

        PageResponseDTO result = new PageResponseDTO(pageRequestDTO, total, list);

        return result;
    }

    @Override
    public UserRoleResponseDTO removeOrgaRole(List<UserRoleReqRemoveUserRoleDTO> removeData) {
        log.info("<< service removeOrgaRole >>");
        UserRoleResponseDTO result = UserRoleResponseDTO.builder().build();
        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicReference<Long> targetOrgaNo = new AtomicReference<>((long) 0);

        removeData.forEach(dto -> {
            targetOrgaNo.set(dto.getTargetOrgaNo());
            if(dto.getOrgaNo() != dto.getTargetOrgaNo()) flag.set(true);
        });

        if(flag.get()){
            result.setState("fail");
            result.setMessage("다른 회사/부서/사원에 부여된 권한은 해제할 수 없습니다.");
        }else{
            List<Long> removeUserRoleList = removeData.stream()
                    .filter(dto -> dto.getTargetOrgaNo() == dto.getOrgaNo() ? true : false)
                    .map(dto -> dto.getOrgaNo())
                    .collect(Collectors.toList());

            log.info("Orga No List");
            removeUserRoleList.forEach(log::info);

            result.setState("success");
            result.setMessage("전달 성공");

//            UserRoleReqRemoveUserRoleDTO2 userRoleReqRemoveUserRoleDTO2 = UserRoleReqRemoveUserRoleDTO2.builder().build();
//            userRoleMapper.removeUserRole(userRoleReqRemoveUserRoleDTO2);
        }

        return result;
    }
}
