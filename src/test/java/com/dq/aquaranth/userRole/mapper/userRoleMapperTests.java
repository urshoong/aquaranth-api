package com.dq.aquaranth.userRole.mapper;

import com.dq.aquaranth.userRole.dto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class userRoleMapperTests {
    @Autowired(required = false)
    UserRoleMapper mapper;

    @Test
    void findCompanyAllTests(){
        List<UserRoleCompanyDTO> list = mapper.findCompany(1L);
        list.forEach(log::info);
    }

    @Test
    void findRoleGroupBasedListTests(){
        UserRoleReqRoleGroupBasedListDTO dto = UserRoleReqRoleGroupBasedListDTO.builder()
                .orgaNo(1L)
//                .roleGroupSearch("기본")
                .build();
        List<UserRoleRoleGroupBasedListDTO> list = mapper.findRoleGroupByCompanyName(dto);
        list.forEach(log::info);
    }

    @Test
    void findRoleGroupBasedUserListTests(){
        log.info("findRoleGroupBasedUserListTests ...");
        UserRoleReqGroupBasedUserListDTO userRoleReqGroupBasedUserListDTO = UserRoleReqGroupBasedUserListDTO.builder()
                .roleGroupNo(3L)
                .companyNo(1L)
//                .userListSearch("개발")
                .build();

        List<UserRoleGroupBasedUserListDTO> list = mapper.findOrgaByRoleGroupNo(userRoleReqGroupBasedUserListDTO);
        list.forEach(log::info);
    }

    @Test
    void insertUserRoleTests(){
        UserRoleReqInsertOrgaRoleDTO input = UserRoleReqInsertOrgaRoleDTO.builder()
                .companyNo(1L)
                .roleGroupNo(4L)
                .orgaNoList(Arrays.asList(19, 20, 21))
                .build();
        log.info(input);
        Integer result = mapper.insertUserRole(input);
        log.info("result :: " + result);
    }

    @Test
    void findUserListByCompanyNoTests(){
        UserRoleReqUserListBasedDTO input = UserRoleReqUserListBasedDTO.builder()
                .orgaNo(1L)
//                .searchEmp("Test")
//                .searchRole("개발")
                .build();
        List<UserRoleUserListBasedDTO> list = mapper.findUserListByOrgaNo(input);
        list.forEach(log::info);
    }

    @Test
    void findByRoleGroupByUserTests(){
        List<UserRoleUserBasedRoleGroupDTO> list = mapper.findByRoleGroupByUser(21L);
        list.forEach(log::info);
    }
}
