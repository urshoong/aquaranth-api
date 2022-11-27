package com.dq.aquaranth;

import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.service.MenuService;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.dq.aquaranth.rolegroup.service.RoleGroupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * application 실행시 처리되는 로직입니다
 *
 * @author jonghyun
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class InitRunner implements ApplicationRunner {
    private final RedisService redisService;
    private final MenuService menuService;
    private final RoleGroupService roleGroupService;

    private final UserSessionService userSessionService;

    /**
     * springboot load 시 실행됩니다.
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initRedis();
        testt();
    }

    /**
     * redis 에 기존에 저장된 데이터를 전부 삭제합니다.
     * 메뉴코드에 매핑되어있는 권한그룹들을 저장합니다.
     */
    public void initRedis() throws JsonProcessingException {
        log.info("Redis init");
        redisService.deleteObject(redisService.keys("*")); // 기존 데이터 삭제

        // db 에 저장된 모든 메뉴를 가져옵니다.
        List<String> menuCodes = new ArrayList<>();
        for (MenuResponseDTO param : menuService.findAllBy(new MenuRequestDTO())) {
            menuCodes.add(param.getMenuCode());
        }

        // 메뉴코드에 매핑된 권한그룹들을 전부 가져와서, menuCode, roleGroupNo 형태로 저장합니다.
        for (String menuCode : menuCodes) {
            List<Long> value = new ArrayList<>();
            roleGroupService.findByMenuCode(menuCode).forEach(roleGroup -> value.add(roleGroup.getRoleGroupNo()));
            redisService.setCacheObject(menuCode, value);
        }
    }

    public void testt(){
        LoginUser loginUser = LoginUser.builder()
                .username("user")
                .loginCompanyNo(1L)
                .loginDeptNo(1L)
                .build();
        userSessionService.loadUserInfoByLoginUser(loginUser);
    }


}
