package com.dq.aquaranth.userRole.service;

import com.dq.aquaranth.userRole.dto.UserRoleCompanyDTO;
import com.dq.aquaranth.userRole.dto.UserRoleReqRoleGroupBasedListDTO;
import com.dq.aquaranth.userRole.dto.UserRoleRoleGroupBasedListDTO;
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
}
