package com.dq.aquaranth.login.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.dto.RedisDTO;

import java.util.Map;

public interface UserSessionService {

    RedisDTO findUserInfoInRedis(String username);

    Map<String, String> checkRefresh(String authorizationHeader);
}
