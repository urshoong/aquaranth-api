package com.dq.aquaranth.login.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.dto.RedisDTO;

public interface UserSessionService {
    CustomUser findUserInfoInDatabase(EmpDTO empDTO);

    RedisDTO findUserInfoInRedis(String username);

}
