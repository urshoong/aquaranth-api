package com.dq.aquaranth.menu.interceptor;

import com.dq.aquaranth.menu.constant.ErrorCode;
import com.dq.aquaranth.menu.exception.MenuException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("인터셉터 요청 가로챔.");
//        throw new MenuException(ErrorCode.MENU_NOT_FOUND);
        return true;
    }
}
