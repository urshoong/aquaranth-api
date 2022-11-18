package com.dq.aquaranth.login.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.JWTUtil;
import com.dq.aquaranth.commons.utils.SendResponseUtils;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.dto.RedisDTO;
import com.dq.aquaranth.login.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.dq.aquaranth.login.jwt.JwtProperties.*;
import static com.dq.aquaranth.login.jwt.JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

// 요청 필터당 하나만 존재하는 필터이기 때문에 application 으로 들어오는 모든 요청을 여기에서 가로챕니다.
// 그리고, 사용자가 application 자원에 access 권한이 있는지를 논리적으로 처리해주면 된다.
@Log4j2
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 로그인 경로인지 확인 (login 은 여기에서 작업할 필요가 없기 때문.) == 아무일도 하지않을거임.
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh") || request.getServletPath().contains("/api/file")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                try {
                    log.info("access token을 검증합니다.");
                    String token = authorizationHeader.substring("Bearer ".length());

                    Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    // 토큰이 유효한지 확인되면, 사용자의 이름을 가져올 수 있습니다.
                    String username = decodedJWT.getSubject(); // token 과 함께 제공되는 사용자 이름을 줍니다.
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class); // token 의 roles 를 파싱하여 들고옴(json 배열로 되있음.)
//                    stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                    CustomUser customUser = (CustomUser) userDetailsService.loadUserByUsername(username);
                    ObjectMapper objectMapper = new ObjectMapper();
                    String loginUserInfo = objectMapper
                            .registerModule(new JavaTimeModule())
                            .writeValueAsString(RedisDTO.builder()
                                    .company(customUser.getCompanyDTO())
                                    .dept(customUser.getDeptDTO())
                                    .emp(customUser.getEmpDTO())
                                    .menuList(customUser.getMenuList())
                                    .build());

                    // 암호가 필요없는 이유는 token 검증을 끝마쳤기 때문에 이미 유효한 token 으로 인증이 된 사용자이다.
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());

                    // SpringSecurity 를 호출한 다음 Context 를 들고와서 인증을 설정한 다음 인증 토큰을 전달합니다.
                    // ex) Security 야! 사용자이름과, 역할(role) 등등이 여기있으니 들고가서 access 할 수 있는 자원도 결정해주고 뭐 알아서 하렴 !
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    log.info("access token 검증이 완료되었습니다");
                    filterChain.doFilter(request, response);
                } catch (Exception exception) {
                    // exception 1 : token 이 유효하지 않을 때 (token 을 확인할 수 없거나, 유효기간이 지났을 경우)
                    log.error("Error logging in: {}", exception.getMessage());
                    // error 던지기
                    SendResponseUtils.sendError(UNAUTHORIZED.value(), "[access_token]" + exception.getMessage(), response);
                }
            } else {
                SendResponseUtils.sendError(UNAUTHORIZED.value(), "[access_token] token is missing", response);
            }
        }
    }

}
