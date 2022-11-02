package com.dq.aqaranth.domain.company.mapper;
import com.dq.aqaranth.domain.company.dto.CompanyListDTO;

import java.util.List;

public interface CompanyMapper {

    //회사코드, 회사명, 대표자명 출력
    List<CompanyListDTO> companyList();
}
