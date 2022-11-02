package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.controller.EmpController;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;

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

    @Test
    void empInsertTest() {
        EmpDTO empDTO = EmpDTO.builder()
                .empName("이요르")
                .username("user09")
                .password("userpwd09")
                .gender("남성")
                .empPhone("0101111486")
                .empAddress("디즈니랜드")
                .empProfile("profileYo")
                .email("user09@naver.com")
                .lastLoginTime(now())
                .lastLoginIp("192.168.500.9")
                .firstHiredate(LocalDate.now())
                .lastRetiredate(null)
                .build();

        log.info(controller.empInsert(empDTO));
    }

    @Test
    void empModifyTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("장조아")
                .empPhone("01077777777")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate02@naver.com")
                .lastLoginTime(now())
                .lastLoginIp("192.168.500.999")
                .lastRetiredate(LocalDate.now())
                .empNo(10L)
                .build();

        log.info(controller.empModify(empUpdateDTO));
    }
}
