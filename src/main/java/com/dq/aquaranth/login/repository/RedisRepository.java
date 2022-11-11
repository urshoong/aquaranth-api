package com.dq.aquaranth.login.repository;

import com.dq.aquaranth.company.dto.CompanyDTO;

public interface RedisRepository {

    CompanyDTO findCompanyByUsername(String username);
}
