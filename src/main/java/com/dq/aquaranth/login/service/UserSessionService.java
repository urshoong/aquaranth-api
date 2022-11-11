package com.dq.aquaranth.login.service;

import com.dq.aquaranth.company.dto.CompanyDTO;

public interface UserSessionService {
    /**
     * 로그인한 사용자가 접속한 회사
     */
    CompanyDTO findCompanyByUsername(String username);
}
