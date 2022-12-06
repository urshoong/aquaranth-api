package com.dq.aquaranth.login.controller;

import com.dq.aquaranth.login.dto.LoginUserInfo;
import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.annotation.MenuCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
@MenuCode
public class UserApiController {
    private final UserSessionService userSessionService;
    private final RedisService redisService;

    @GetMapping("/token/refresh")
    public Map<String, String> refreshTokenCheck(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return userSessionService.checkRefresh(authorizationHeader);
    }

    @GetMapping("/login/userinfo")
    public LoginUserInfo getLoginUserInfo(Authentication authentication) {
        return userSessionService.findUserInfoInRedis(authentication.getName());
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication) {
        String username = authentication.getName();

        log.info("{}님이 로그아웃 하였습니다.", username);
        redisService.deleteObject(username);
        return "success";
    }
}
