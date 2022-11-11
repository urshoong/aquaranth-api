package com.dq.aquaranth.login.repository;

import com.dq.aquaranth.company.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {
    @Override
    public CompanyDTO findCompanyByUsername(String username) {
        return null;
    }
}
