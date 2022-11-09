package com.dq.aquaranth.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.SendResponseUtils;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.login.jwt.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final RedisTemplate<String, String> redisTemplate;

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

//        Fixme : user 는 여러개의 권한을 가질 수 있어야 한다.
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public EmpDTO create(EmpDTO emp, HttpServletResponse response) throws IllegalAccessException, IOException {
        log.info("계정을 생성합니다. username => {}", emp.getUsername());

        // 이미 가입된 유저라면
        if (Objects.nonNull(empMapper.findByUsername(emp.getUsername()))) {
            log.error("이미 가입된 유저입니다. username -> " + emp.getUsername());

            // error 전송
            SendResponseUtils.sendError(CONFLICT.value(), "이미 가입된 유저입니다.", response);

            // 회원가입 시 아이디 중복은 흔한 일인데 이걸 굳이 exception 을 던져서 프로그램을 뻗다이 시켜야하는가?
//            return new User();
            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + emp.getUsername());
        }

        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        empMapper.empInsert(emp);
        return empMapper.empFindById(emp.getEmpNo());
    }

    public void checkRefresh(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        log.info("refresh token 을 검증합니다.");

        // 사용자가 토큰을 갱신할 수 있도록 요청을 설정할 수 있는 다른 끝점을 만듭니다.
        // (refresh token 을 Client 가 보내면 그것을 받아서 만료기간을 확인 후 다른 access token 을 부여할 것입니다.)
        // 인증 헤더 찾기
        // Client 가 refresh token 을 보낼때 Bearer 과 함께 보낼 거임.
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                // token 검증 작업
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                // 토큰이 유효한지 확인되면, 사용자의 이름을 가져올 수 있습니다.
                String username = decodedJWT.getSubject(); // token 과 함께 제공되는 사용자 이름을 줍니다.
                UserDetails user = loadUserByUsername(username);

                // Redis에서 저장된 Refresh Token 값을 가져온다.
                String refreshTokenInDatabase = redisTemplate.opsForValue().get(username);

                if (!Objects.equals(refreshToken, refreshTokenInDatabase)) {
                    throw new IllegalAccessException();
                }

//                refresh token 이 유효한 경우, 두개다 다시 재발급해서 클라이언트한테 던져줌
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                                Collectors.toList()))
                        .sign(algorithm);

                refreshToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                /* token body 로 던지기 */
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);

                // RefreshToken Redis에 업데이트
                redisTemplate.opsForValue().set(
                        user.getUsername(),
                        refreshToken,
                        JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME,
                        TimeUnit.MILLISECONDS
                );

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                // exception 1 : token 이 유효하지 않을 때 (token 을 확인할 수 없거나, 유효기간이 지났을 경우)
                response.setHeader("error", exception.getMessage());

                /* error 를 body 로 던지기 (둘중 하나만 할 수 있음) */
                response.setStatus(UNAUTHORIZED.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
