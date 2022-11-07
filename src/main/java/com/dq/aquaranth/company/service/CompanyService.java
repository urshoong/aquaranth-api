package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyListDTO> findAll();
    CompanyDTO findById(Long companyNo);
    Long insert(CompanyDTO companyDTO);
    Long update(CompanyModifyDTO companyModifyDTO);
    Long deleteById(Long companyNo);
}
