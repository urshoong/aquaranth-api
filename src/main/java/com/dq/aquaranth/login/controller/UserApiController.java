package com.dq.aquaranth.login.controller;

import com.dq.aquaranth.commons.utils.ResponseUtil;
import com.dq.aquaranth.company.dto.CompanyInformationDTO;
import com.dq.aquaranth.login.dto.LoginUserInfo;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.constant.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    private final UserSessionService userSessionService;

    @GetMapping("/token/refresh")
    public Map<String, String> refreshTokenCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        Map<String, String> tokens = null;

        try {
            tokens = userSessionService.checkRefresh(authorizationHeader);
            if (Objects.isNull(tokens)) ResponseUtil.sendError(response, ErrorCodes.TOKEN_MISSING);
        } catch (Exception e) {
            log.error("[refresh_token] 검증실패 이유 : {}", e.getMessage());
            ResponseUtil.sendError(response, ErrorCodes.TOKEN_EXPIRED);
        }
        return tokens;
    }

    @GetMapping("/login/company")
    public CompanyInformationDTO getLoginUserCompany(Authentication authentication) {
        return userSessionService.findLoginUserCompany(authentication.getName());
    }

    @GetMapping("/login/userinfo")
    public LoginUserInfo getLoginUserInfo(Authentication authentication) {
        return userSessionService.findUserInfoInRedis(authentication.getName());
    }
}
