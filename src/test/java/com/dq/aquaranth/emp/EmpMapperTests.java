package com.dq.aquaranth.emp;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;
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
    EmpMapper mapper;

    @Test
    void empListTest(){
        log.info(mapper.empList());
    }

    @Test
    void empReadTest(){
        log.info(mapper.empRead(1L));
    }

    @Test
    void empInsertTest(){
        EmpDTO empDTO = EmpDTO.builder()
                .empName("이선율")
                .username("user08")
                .password("userpwd08")
                .gender("남성")
                .empPhone("01088890009")
                .empAddress("경주시 경주월드")
                .empProfile("profileYul")
                .email("user08@naver.com")
                .lastLoginTime(now())
                .lastLoginIp("192.168.500.8")
                .firstHiredate(LocalDate.now())
                .lastRetiredate(null)
                .build();
        log.info(mapper.empInsert(empDTO));
    }

    @Test
    void empModifyTest(){
        EmpUpdateDTO empUpdateDTO = EmpUpdateDTO.builder()
                .empName("정수정")
                .empPhone("01011111111")
                .empAddress("수정시 수정구")
                .empProfile("profileUpdate")
                .email("userUpdate01@naver.com")
                .lastLoginTime(now())
                .lastLoginIp("192.168.500.999")
                .lastRetiredate(LocalDate.now())
                .empNo(10L)
                .build();

        log.info(mapper.empModify(empUpdateDTO));
    }

    @Test
    void empDeleteTest() {
        log.info(mapper.empDelete(11L));
    }
}
