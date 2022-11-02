package com.dq.aqaranth.domain.tests.mapper;


import com.dq.aqaranth.domain.tests.dto.Company;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper {
    @Select("select * from company")
    List<Company> findAll();
}
