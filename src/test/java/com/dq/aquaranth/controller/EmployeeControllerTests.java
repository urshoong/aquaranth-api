package com.dq.aquaranth.controller;

import com.dq.aquaranth.employee.controller.EmployeeController;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmployeeControllerTests {

    @Autowired(required = false)
    EmployeeController controller;

    @Test
    void employeeListTest() {
        log.info(controller.employeeList());
    }
}
