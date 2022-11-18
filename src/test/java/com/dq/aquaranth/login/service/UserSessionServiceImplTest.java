package com.dq.aquaranth.login.service;

import com.dq.aquaranth.login.dto.RedisDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class UserSessionServiceImplTest {
    @Autowired
    private UserSessionService userSessionService;

    @Test
    void findUserInfoInDatabase() {
    }

    @Test
    void findUserInfoInRedis() {

        RedisDTO result = userSessionService.findUserInfoInRedis("admin");

        log.info(result);
    }
}
