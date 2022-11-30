package com.dq.aquaranth.login.aop;

import com.dq.aquaranth.login.annotation.RedisUpdate;
import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.dto.LoginUserInfoDTO;
import com.dq.aquaranth.login.service.UserSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * {@code @RedisUpdate} 어노테이션이 붙은 메서드가 실행되기 전 동작하는 AOP 입니다.
 *
 * @author jonghyun
 */
@Component
@Aspect
@RequiredArgsConstructor
@Log4j2
public class RedisUpdateAop {
    private final UserSessionService userSessionService;

    /**
     * point cut 을 지정할 패키지 경로입니다.
     */
    @Pointcut("execution(* com.dq.aquaranth..*.*(..))")
    private void cut(){}

    /**
     * pointcut 을 지정한 하위패키지에 있는 @RedisUpdate 어노테이션이 붙은 메서드가 실행된 이후에 동작합니다.
     *
     */
    @After("cut() && @annotation(redisUpdate)")
    public void after(RedisUpdate redisUpdate) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String username = request.getUserPrincipal().getName();
        LoginUserInfoDTO loginUserInfoDTO = userSessionService.findUserInfoInRedis(username);

        LoginUserInfoDTO savedLoginUserInfoDTO = userSessionService.loadUserInfoByLoginUser(LoginUser.builder()
                .loginDeptNo(loginUserInfoDTO.getDept().getDeptNo())
                .loginCompanyNo(loginUserInfoDTO.getCompany().getCompanyNo())
                .username(username)
                .build());

        log.info("Redis 에 {}님의 정보가 업데이트 되었습니다. => {}", username, savedLoginUserInfoDTO);
    }
}
