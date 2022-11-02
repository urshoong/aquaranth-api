package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmpServiceTests {

    @Autowired(required = false)
    EmpService service;

    @Test
    void empListTest(){
        log.info(service.empList());
    }

    @Test
    void empRead(){
        log.info(service.empRead(1L));
    }
}
