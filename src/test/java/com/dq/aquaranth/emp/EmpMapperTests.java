package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static java.time.LocalDateTime.now;

@SpringBootTest
@Log4j2
public class EmpMapperTests {

    @Autowired(required = false)
    EmpMapper empMapper;

    @Test
    void empFindAllTest(){
        log.info(empMapper.empFindAll());
    }

    @Test
    void empFindByIdTest(){
        log.info(empMapper.empFindById(1L));
    }

    @Test
    void empFindOrgaTest() {
        log.info(empMapper.empFindOrga(1L));
    }

//    @Test
//    void empInsertTest(){
//        EmpDTO empDTO = EmpDTO.builder()
//                .empName("김바보")
//                .username("user08")
//                .password("userpwd08")
//                .gender("남성")
//                .empPhone("01088890009")
//                .empAddress("경주시 경주월드")
//                .empProfile("profileYul")
//                .email("user08@naver.com")
//                .lastLoginTime(now())
//                .lastLoginIp("192.168.500.8")
//                .lastRetiredate(null)
//                .build();
//        log.info(empMapper.empInsert(empDTO));
//    }
//
//    @Test
//    void empOrgaInsertTest(){
//        EmpOrgaDTO empOrgaInsertDTO = EmpOrgaDTO
//                .builder()
//                .deptNo(5L)
//                .orgaType("emp")
//                .build();
//
//        empMapper.empOrgaInsert(empOrgaInsertDTO);
//    }
//
//    @Test
//    void empMappingInsertTest(){
//        EmpMappingDTO empMappingDTO = EmpMappingDTO
//                .builder()
//                .orgaNo(24L)
//                .empNo(1L)
//                .empRank("사원")
//                .hiredate(LocalDate.now())
//                .build();
//
//        empMapper.empMappingInsert(empMappingDTO);
//    }

    @Test
    void empUpdateTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("정수정")
                .password("gi")
                .gender("여성")
                .empPhone("01011111111")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate01@naver.com")
                .lastRetiredate(LocalDate.now())
                .empNo(22L)
                .build();

        log.info(empMapper.empUpdate(empUpdateDTO));
    }

    @Test
    void empDeleteByIdTest() {
        log.info(empMapper.empDeleteById(11L));
    }

    @Test
    void empCountAllTest(){
        log.info(empMapper.empCountAll());
    }

}
