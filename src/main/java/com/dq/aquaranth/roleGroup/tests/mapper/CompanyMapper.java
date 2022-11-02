package com.dq.aquaranth.roleGroup.tests.mapper;


import com.dq.aquaranth.roleGroup.tests.dto.Company;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper {
    @Select("select * from company")
    List<Company> findAll();
}
