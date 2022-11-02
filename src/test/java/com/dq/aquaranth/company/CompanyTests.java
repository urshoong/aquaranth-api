package com.dq.aquaranth.company;

import com.dq.aquaranth.company.controller.CompanyController;
import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Log4j2
public class CompanyTests {

    @Autowired(required = false)
    CompanyController companyController;

    @Test
    @DisplayName("회사코드, 회사명, 대표자명, 사용여부 리스트 출력 Test 코드")
    void companyListTest() {
        List<CompanyListDTO> companyDTO = companyController.companyList();
        companyDTO.forEach(log::info);
    }

    @Test
    @DisplayName("회사 기본정보 출력 Test 코드")
    void companyInformationTest() {
        long companyNo = 1L;
        CompanyDTO companyDTO = companyController.companyInformation(companyNo);
        log.info(companyDTO);
    }

    @Test
    @DisplayName("회사 기본정보 추가 Test 코드")
    void companyAddTest() {
        LocalDate localDate = LocalDate.of(2011, 01, 05);
        CompanyDTO companyDTO = CompanyDTO
                .builder()
                .companyName("WINS")
                .companyAddress("경기도 성남시 분당구 판교로228번길 15")
                .companyTel("031-622-8600")
                .ownerName("김보연")
                .foundingDate(localDate)
                .businessNumber("129-86-549371")
                .companyUse(false)
                .build();
        log.info(companyController.companyAdd(companyDTO));

        //등록 시 저장되는 회사코드 출력
        log.info(companyDTO.getCompanyNo());
    }

    @Test
    @DisplayName("회사 기본정보 수정 Test 코드")
    void companyModifyTest() {
        CompanyModifyDTO companyModifyDTO = CompanyModifyDTO
                .builder()
                .companyNo(7L)
                .companyName("MOMSTOUCH")
                .companyAddress("서울특별시 강동구 천호대로 1077")
                .companyTel("02-418-8884")
                .ownerName("김동전")
                .companyUse(true)
                .build();
        log.info(companyController.companyModify(companyModifyDTO));
    }

    @Test
    @DisplayName("회사 정보 삭제 Test 코드")
    void companyDeleteTest() {
        long companyNo = 7L;
        log.info(companyController.companyDelete(companyNo));
    }
}
