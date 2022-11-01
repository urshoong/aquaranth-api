package com.dq.aqaranth.service;

import com.dq.aqaranth.domain.employee.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmployeeServiceTests {

    @Autowired(required = false)
    EmployeeService service;

    @Test
    void employeeListTest(){
        log.info(service.employeeList());
    }
}
