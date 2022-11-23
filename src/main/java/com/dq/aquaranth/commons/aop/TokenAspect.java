package com.dq.aquaranth.commons.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * 토큰을 자동으로 파싱 및 검증하여 필요한 컨트롤러에 파라미터로 전달합니다.
 *
 * @author 임종현
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TokenAspect {


    /**
     * 토큰을 파싱 및 검증합니다.
     *
     * @param pjp - 메서드 원본의 정보를 가지고있는 객체입니다.
     * @throws Throwable 메서드를 실행시킬 때 발생할 수 있는 예외입니다.
     * @author 임종현
     */
    @Before("execution(* com.dq.aquaranth.menu.controller.*.*(.., @com.dq.aquaranth.commons.annotation.Test123 (*), ..))")
    public void parseToken(JoinPoint joinPoint) throws Throwable {
        log.info("W@@@@@@@@@@@@@@@@@@@@@@@2222: {}", joinPoint.getSignature().getName());

    }

}
