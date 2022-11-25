package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.emp.mapper.EmpMappingMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
@Log4j2
public class EmpMapperTests {

    @Autowired(required = false)
    EmpMapper empMapper;
    EmpMappingMapper empMappingMapper;

    @Test
    void empFindAllTest(){
        log.info(empMapper.findAll());
    }

    @Test
    void empFindByIdTest(){
        log.info(empMapper.findById(1L));
    }

    @Test
    void empOrgaFindByIdTest() {
        log.info(empMapper.orgaFindById(1L));
    }

    @Test
    void empInsertTest(){
        EmpDTO empDTO = EmpDTO.builder()
                .empName("김바보")
                .username("user08")
                .password("userpwd08")
                .gender("남성")
                .empPhone("01088890009")
                .empAddress("경주시 경주월드")
                .email("user08@naver.com")
                .build();
        log.info(empMapper.insert(empDTO));
    }

    @Test
    void empOrgaInsertTest(){
        EmpOrgaDTO empOrgaInsertDTO = EmpOrgaDTO
                .builder()
                .deptNo(5L)
                .build();

        empMapper.orgaInsert(empOrgaInsertDTO);
    }

    @Test
    void empMappingInsertTest(){
        EmpMappingDTO empMappingDTO = EmpMappingDTO
                .builder()
                .orgaNo(24L)
                .empNo(1L)
                .empRank("사원")
                .build();

        empMappingMapper.empMappingInsert(empMappingDTO);
    }

    @Test
    void empUpdateTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("정수정")
                .gender("여성")
                .empPhone("01011111111")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate01@naver.com")
                .lastRetiredDate(LocalDate.now())
                .empNo(22L)
                .build();

        log.info(empMapper.update(empUpdateDTO));
    }

    @Test
    void orgaUpdateTest() {
        EmpOrgaUpdateDTO empOrgaUpdateDTO =
                EmpOrgaUpdateDTO.builder()
                        .empRank("인턴")
                        .empRole("ROLE_USER")
                        .deptNo(1L)
                        .orgaNo(40L)
                        .build();

        empMapper.orgaUpdate(empOrgaUpdateDTO);
    }
    @Test
    void mapTests(){
        log.info(empMapper.findLoginUser("emp01"));
    }

}
