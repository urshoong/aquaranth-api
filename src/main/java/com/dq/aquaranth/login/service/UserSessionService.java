package com.dq.aquaranth.login.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.dto.LoginUserInfo;

import java.util.Map;

public interface UserSessionService {

    LoginUserInfo findUserInfoInRedis(String username);

    Map<String, String> checkRefresh(String authorizationHeader);

    LoginUserInfo loadUserInfoByLoginUser(LoginUser loginUser);

    CompanyDTO findLoginUserCompany(String username);
}
