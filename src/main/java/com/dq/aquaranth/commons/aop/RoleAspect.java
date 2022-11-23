package com.dq.aquaranth.commons.aop;

import com.dq.aquaranth.login.domain.CustomUser;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 어떤 컨트롤러에 진입했는지 로그를 남기는 클래스입니다.
 *
 * @author 임종현
 */
@Log4j2
@Aspect
@Component
public class RoleAspect {

    /**
     * 진입 메서드에 대한 로그를 남깁니다.
     * @param jp - 메서드 정보
     */
    @Before("execution(* com.dq.aquaranth..controller..*.*(..))")
    public void checkRole(JoinPoint jp) {
//        TODO:     1. redis 에서 username 으로 권한목록 들고온다. (최초 로그인시 올라가있는 내용)
//                  2. security context 에 있는 customUser 안의 권한목록을 가져온다. --> 업데이트된 권한들? (디비에서 뒤짐)
//                  3. 두개를 비교해서
//                  2.
//                  2.
//
        log.info("[ROLE ASPECT]{} 컨트롤러 ", jp.getSignature().getName());
//        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(customUser);
    }
}
