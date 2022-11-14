package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.*;

import java.util.List;

public interface CompanyService {

    List<CompanyListDTO> findAll();
    CompanyDTO findById(Long companyNo);
    Long insert(CompanyDTO companyDTO);
    Long update(CompanyModifyDTO companyModifyDTO);
    Long deleteById(Long companyNo);
    List<CompanyListDTO> search(Boolean companyUse, String companySearch);
    List<OrgaTreeDTO> orgaTree();
    List<OrgaEmpDTO> findAllEmp(OrgaEmpSearchDTO orgaEmpSearchDTO);
    OrgaEmpInformationDTO findEmpInformation(Long empNo);
}

