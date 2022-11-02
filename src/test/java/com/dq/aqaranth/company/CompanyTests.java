package com.dq.aqaranth.company;

import com.dq.aqaranth.domain.company.controller.CompanyController;
import com.dq.aqaranth.domain.company.dto.CompanyListDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class CompanyTests {

    @Autowired(required = false)
    CompanyController companyController;

    @Test
    void companyListTest() {
        List<CompanyListDTO> companyDTO = companyController.companyList();
        companyDTO.forEach(log::info);
    }
}
