package com.dq.aquaranth.userRole.service;

import com.dq.aquaranth.userRole.dto.*;
import com.dq.aquaranth.userRole.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleCompanyDTO> findCompany(Long companyNo) {
        return userRoleMapper.findCompany(companyNo);
    }

    public List<UserRoleRoleGroupBasedListDTO> findRoleGroupByCompanyName(UserRoleReqRoleGroupBasedListDTO userRoleReqRoleGroupBasedListDTO){
        return userRoleMapper.findRoleGroupByCompanyName(userRoleReqRoleGroupBasedListDTO);
    }

    @Override
    public List<UserRoleGroupBasedUserListDTO> findOrgaByRoleGroupNo(UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO) {
        log.info("<<< findOrgaByRoleGroupNo >>>");
        log.info(userRoleReqGroupBasedUserListDTO);
        List<UserRoleGroupBasedUserListDTO> list = userRoleMapper.findOrgaByRoleGroupNo(userRoleReqGroupBasedUserListDTO);
        log.info(list);
        return list;
    }

    @Override
    public Integer insertUserRole(UserRoleReqInsertOrgaRoleDTO userRoleReqInsertOrgaRoleDTO) {
        return userRoleMapper.insertUserRole(userRoleReqInsertOrgaRoleDTO);
    }

    @Override
    public Integer removeUserRole(UserRoleReqRemoveOrgaRoleDTO userRoleReqRemoveOrgaRoleDTO) {
        return userRoleMapper.removeUserRole(userRoleReqRemoveOrgaRoleDTO);
    }
}
