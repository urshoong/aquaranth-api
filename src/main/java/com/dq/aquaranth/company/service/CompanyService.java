package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {

    private final CompanyMapper companyMapper;

    //회사코드, 회사명, 대표자명 출력
    public List<CompanyListDTO> companyList() {
        return companyMapper.companyList();
    }
}