package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class EmpMapperTests {

    @Autowired(required = false)
    EmpMapper mapper;

    @Test
    void empListTest(){
        log.info(mapper.empList());
    }

    @Test
    void empSelectOneTest(){
        log.info(mapper.empSelectOne(1L));
    }
}
