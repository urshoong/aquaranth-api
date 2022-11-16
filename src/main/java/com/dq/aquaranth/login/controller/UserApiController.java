package com.dq.aquaranth.login.controller;

import com.dq.aquaranth.commons.utils.SendResponseUtils;
import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.service.UserService;
import com.dq.aquaranth.login.service.UserSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;
    private final UserSessionService userSessionService;

    @GetMapping("/token/refresh")
    public Map<String, String> refreshTokenCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        Map<String, String> tokens = null;

        try {
            tokens = userService.checkRefresh(authorizationHeader);
        } catch (Exception e) {
            log.error("[refresh_token] 검증실패 이유 : {}", e.getMessage());
            SendResponseUtils.sendError(HttpStatus.UNAUTHORIZED.value(), "[refresh_token]" + e.getMessage(), response);
        }
        return tokens;
    }

    @GetMapping("/login/company")
    public CompanyDTO getLoginUserCompany(Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return customUser.getCompanyDTO();
    }
}
