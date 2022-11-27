package com.dq.aquaranth.menu.interceptor;

import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.constant.ErrorCode;
import com.dq.aquaranth.menu.exception.MenuException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {


    private final UserSessionService userSessionService;
    private final RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info(request.getHeader("Menu"));
//        log.info(redisService.getCacheObject("ROLE0030"));
        return true;
    }
}
