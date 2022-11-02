package com.dq.aquaranth.userRole.mapper;

import com.dq.aquaranth.company.dto.CompanyDTO;
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
    void getCompanyNames(){
        List<CompanyDTO> list = mapper.getCompanyList();
    }
}
