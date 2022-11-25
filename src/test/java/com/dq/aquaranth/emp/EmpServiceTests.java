package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class EmpServiceTests {

    @Autowired(required = false)
    EmpService service;

    @Test
    void empListTest(){
        log.info(service.findAll());
    }

    @Test
    void empReadTest(){
        log.info(service.findById(1L));
    }

    @Test
    void empOrgaListTest() {
        log.info(service.findAllOrga(1L));
    }



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
                .lastRetiredDate(null)
                .empNo(16L)
                .build();

        log.info(service.update(empUpdateDTO));
    }

    @Test
    void empInsertTest() throws IllegalAccessException{
        String registrant = "종현";

        // 조직
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(8L)
                .regUser(registrant) //등록자
                .build();

        Long orgaId = orgaDTO.getOrgaNo();

        // 사원
        EmpDTO empDTO = EmpDTO.builder()
                .empName("admin") //이름
                .username("admin") //id
                .password("admin") //비번
                .gender("m")   //성별
                .empPhone("01011111111") //휴대폰 번호
                .empAddress("부산시") //주소
                .email("email@naver.com") //이메일
                .firstHiredDate(LocalDate.now())//첫입사일
                .regUser(registrant) //등록자
                .build();

        Long empId = empDTO.getEmpNo();

        // 매핑테이블
        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .orgaNo(orgaId)
                .empNo(empId)
                .empRole("ROLE_USER") //사용자권한
                .empRank("사원") //직급
                .regUser(registrant)
                .build();

            service.insert(orgaDTO, empDTO, empMappingDTO);
    }

    @Test
    void registerEmpOrga() {
        String registrant = "종현";
        Long empNo = 14L;
        // 조직
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(6L)
                .regUser(registrant) //등록자
                .build();

        Long orgaId = orgaDTO.getOrgaNo();

        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .orgaNo(orgaId)
                .empNo(empNo)
                .empRole("ROLE_USER") //사용자권한
                .empRank("사원") //직급
                .regUser(registrant)
                .build();

        service.empOrgaInsert(orgaDTO, empMappingDTO, empNo);
    }


    ///////////////////

    @Test
    void insertEmpTest() {
        EmpDTO empDTO = EmpDTO.builder()
                .empName("박경민")
                .username("pkm")
                .password("pkm")
                .gender("m")
                .regUser("admin")
                .firstHiredDate(LocalDate.now())
                .build();

        service.insertEmp(empDTO);
    }

}
