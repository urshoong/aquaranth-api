package com.dq.aquaranth.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.JWTUtil;
import com.dq.aquaranth.commons.utils.SendResponseUtils;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.jwt.JwtProperties;
import com.dq.aquaranth.menu.dto.response.AllMenuResponse;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dq.aquaranth.login.jwt.JwtProperties.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final EmpMapper empMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JWTUtil jwtUtil;
    private final MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("사용자가 로그인을 시도합니다. username => {}", username);

        // find user
        EmpDTO user = empMapper.findByUsername(username);

        if (user == null) {
            log.error("user 정보가 db에 존재하지 않습니다.");
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("user 정보를 찾았습니다 username => {}", username);
        return new CustomUser(user, menuMapper.findMenusByLoginUsername(username));
    }

    public Map<String, String> checkRefresh(String authorizationHeader) throws Exception {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.info("refresh token 을 검증합니다.");
            String refreshToken = authorizationHeader.substring(TOKEN_PREFIX.length());
            Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);

            // 토큰이 유효한지 확인되면, 사용자의 이름을 가져올 수 있습니다.
            String username = decodedJWT.getSubject(); // token 과 함께 제공되는 사용자 이름을 줍니다.

            log.info("refresh token 검증이 완료되었습니다.");

            return jwtUtil.generateToken(username);
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    public ArrayList<String> findMenusByLoginUsername(String username) {
        return menuMapper.findMenusByLoginUsername(username);
    }
}
