package com.dq.aquaranth.login.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;

public interface UserSessionService {
    CustomUser findUserInfoInDatabase(EmpDTO empDTO);

    CustomUser findUserInfoInRedis(String username);
}
