package com.dq.aquaranth.menu.interceptor;

import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.constant.MenuCodes;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {


    private final UserSessionService userSessionService;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;


        String menuCode = handlerMethod.getBean().getClass().getDeclaredAnnotation(MenuCode.class).value().getCode();

        if (menuCode.equals(MenuCodes.ROOT.getCode())){
            return true;
        }

        String username = request.getUserPrincipal().getName();

        List<RoleGroup> loginUserInfo = userSessionService.findUserInfoInRedis(username)
                .getRoleGroups();


        List<RoleGroup> menuRoles = objectMapper.readValue(redisTemplate.opsForValue().get(menuCode).toString(), new TypeReference<>(){});


        loginUserInfo.stream()
                .filter(loginUser -> menuRoles.stream().anyMatch(menuRole -> loginUser.getRoleGroupNo().equals(menuRole.getRoleGroupNo())))
                .findAny()
                .orElseThrow(() -> new MenuException(ErrorCodes.UNAUTHORIZED_MEMBER));

        return true;
    }
}
