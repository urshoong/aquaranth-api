package com.dq.aquaranth.login.jwt;

import com.dq.aquaranth.commons.utils.JWTUtil;
import com.dq.aquaranth.login.dto.LoginReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// 로그인이 성공할 때마다 access token 과 refresh token 을 발급해줄 filter
@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JWTUtil jwtUtil;


    /**
     * 인증 시도 호출되는 메서드
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // request에 있는 username과 password를 파싱해서 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();
        LoginReqDTO dto = null;

        try {
            dto = om.readValue(request.getInputStream(), LoginReqDTO.class);
        } catch (Exception e) {
            log.error("로그인 데이터를 읽어오던 중 오류가 발생했습니다. message => {}", e.getMessage());
        }

        if (Objects.isNull(dto)) {
            log.error("전달받은 login 객체가 없습니다.");
        }

        String username = Objects.requireNonNull(dto).getUsername();
        String password = dto.getPassword();

        log.info("Username is : {}", username);
        log.info("password is : {}", password);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증 성공시(로그인이 되었을 때 호출 될 메서드),
     * 사용자에게 access token 과,
     * 성공적으로 로그인 한 후 refresh token 을 제공하는 메서드
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        String username = authentication.getName();
        log.info("{} 님이 로그인 하였습니다.", username);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), jwtUtil.generateToken(username));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.warn(failed.getMessage());
        response.sendError(499, failed.getMessage());
    }
}
