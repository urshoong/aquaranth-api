package com.dq.aquaranth.login.controller;

import com.dq.aquaranth.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;

    @GetMapping("/token/refresh")
    public void refreshTokenCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.checkRefresh(request, response);
    }

}
