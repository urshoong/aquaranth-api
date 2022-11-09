package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;

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
    void empReadTest(){
        log.info(service.empRead(1L));
    }

    @Test
    void empOrgaListTest() {
        log.info(service.empOrgaList(1L));
    }

//    @Test
//    void empInsertTest(){
//        EmpDTO empDTO = EmpDTO.builder()
//                .empName("Annie")
//                .username("user09")
//                .password("userpwd09")
//                .gender("여성")
//                .empPhone("01088776655")
//                .empAddress("에버랜드")
//                .empProfile("profile")
//                .email("user09@naver.com")
//                .lastLoginTime(now())
//                .lastLoginIp("192.168.500.9")
//                .lastRetiredate(null)
//                .build();
//        log.info(service.empRegister(empDTO));
//
//    }

    @Test
    void empModifyTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("양당근")
                .password("modTest")
                .gender("여성")
                .empPhone("01011112222")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate02@naver.com")
                .lastRetiredate(null)
                .empNo(16L)
                .build();

        log.info(service.empModify(empUpdateDTO));
    }

    @Test
    void empDeleteTest() {
        log.info(service.empRemove(16L));
    }


    @Test
    void registerMethodTest() {
        EmpInsertInformationDTO empInsertInformationDTO
                = EmpInsertInformationDTO
                .builder()
                .deptNo(2L)
                .orgaType("emp")
                .empName("양당근")
                .username("user")
                .password("userpwd")
                .gender("여성")
                .empProfile("profile")
                .empPhone("01099231293")
                .empAddress("울산")
                .empProfile("file")
                .email("email@naver.com")
                .lastLoginTime(now())
                .lastLoginIp("192.168.12.11")
                .firstHiredate(LocalDate.now())
                .lastRetiredate(null)
                .empRank("사원")
                .hiredate(LocalDate.now())
                .build();

        service.empRegister(empInsertInformationDTO);
    }
}
