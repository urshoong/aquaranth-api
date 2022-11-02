package com.dq.aquaranth.company.controller;

import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    //회사코드, 회사명, 대표자명 출력
    @GetMapping("/comlist")
    public List<CompanyListDTO> companyList() {
        return companyService.companyList();
    }
}
