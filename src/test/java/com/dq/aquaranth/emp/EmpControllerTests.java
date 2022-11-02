package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.controller.EmpController;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmpControllerTests {

    @Autowired(required = false)
    EmpController controller;

    @Test
    void empListTest() {
        log.info(controller.empList());
    }

    @Test
    void empReadTest() {
        log.info(controller.empRead(1L));
    }
}
