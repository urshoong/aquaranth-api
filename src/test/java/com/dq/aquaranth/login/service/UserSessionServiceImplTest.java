package com.dq.aquaranth.login.service;

import com.dq.aquaranth.login.constant.RedisKeys;
import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.dto.LoginUserInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class UserSessionServiceImplTest {
    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    RedisService redisService;

    @Test
    void findUserInfoInDatabase() {
    }

    @Test
    void findUserInfoInRedis() {

        LoginUserInfo result = userSessionService.findUserInfoInRedis("user");

        log.info(result);
    }

    @Test
    @DisplayName("redis에 로그인한사원의 정보를 저장합니다.")
    void loadUserInfoByLoginUser() {
        // given
        String username = "tndus";
        Long companyNo = 7L; // MEGAZONE(orgaNo=2)
        Long deptNo = 7L; // 개발팀(orgaNo=7)

        LoginUser loginUser = LoginUser.builder()
                .loginCompanyNo(companyNo)
                .loginDeptNo(deptNo)
                .username(username).build();

        // when
        userSessionService.loadUserInfoByLoginUser(loginUser);
    }

    @Test
    @DisplayName("로그아웃")
    void logout() {
        String username = RedisKeys.USER_KEY.getKey() + "tndus";
        redisService.deleteObject(username);
    }
}
