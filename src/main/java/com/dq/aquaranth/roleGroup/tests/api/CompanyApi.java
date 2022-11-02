package com.dq.aquaranth.roleGroup.tests.api;


import com.dq.aquaranth.roleGroup.tests.dto.Company;
import com.dq.aquaranth.roleGroup.tests.mapper.CompanyMapper;
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
public class CompanyApi {
    private final CompanyMapper companyMapper;

    @GetMapping("/list")
    public List<Company> getCompanyList() {
        List<Company> companyList = companyMapper.findAll();
        log.info(companyList);
        return companyList;
    }
}


