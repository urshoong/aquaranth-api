package com.dq.aquaranth.userrole.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                .companyName("DOUZONE")
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

}
