package com.dq.aqaranth.mapper;

import com.dq.aqaranth.domain.employee.mapper.EmployeeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmployeeMapperTests {

    @Autowired(required = false)
    EmployeeMapper mapper;

    @Test
    void employeeListTest(){
        log.info(mapper.employeeList());
    }
}
