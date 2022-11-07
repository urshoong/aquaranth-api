package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;
import com.dq.aquaranth.company.dto.CompanySearchDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyListDTO> findAll();
    CompanyDTO findById(Long companyNo);
    Long register(CompanyDTO companyDTO);
    Long modify(CompanyModifyDTO companyModifyDTO);
    Long removeById(Long companyNo);

    List<CompanyListDTO> search(Boolean companyUse, String companySearch);
}
