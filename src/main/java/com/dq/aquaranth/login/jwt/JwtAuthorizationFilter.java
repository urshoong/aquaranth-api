package com.dq.aquaranth.login.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.ResponseUtil;
import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.exception.MenuException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.dq.aquaranth.login.jwt.JwtProperties.SECRET;
import static com.dq.aquaranth.login.jwt.JwtProperties.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 요청 필터당 하나만 존재하는 필터이기 때문에 application 으로 들어오는 모든 요청을 여기에서 가로챕니다.
 * 사용자가 application 자원에 access 권한이 있는지를 논리적으로 처리해줍니다.
 *
 * @author 임종현
 */
@Log4j2
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * access token 의 유효성 검증을 처리합니다.
     * 시큐리티 컨텍스트에 인증시도한 유저정보를 저장합니다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException  {
        // 1. 로그인 경로인지 확인 (login 은 여기에서 작업할 필요가 없기 때문.) == 아무일도 하지않을거임.
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")
                || request.getServletPath().contains("/api/file") || request.getServletPath().contains("/swagger-ui/")) {
            log.info("=============== login 요청받음 ========================");
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

                    // 암호가 필요없는 이유는 token 검증을 끝마쳤기 때문에 이미 유효한 token 으로 인증이 된 사용자이다.
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                    // SpringSecurity 를 호출한 다음 Context 를 들고와서 인증을 설정한 다음 인증 토큰을 전달합니다.
                    // ex) Security 야! 사용자이름과, 역할(role) 등등이 여기있으니 들고가서 access 할 수 있는 자원도 결정해주고 뭐 알아서 하렴 !
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (JwtException jwtException) {
                    log.error("token 이 유효하지 않습니다. (token 을 확인할 수 없거나, 유효기간이 지났을 경우) {}", jwtException.getMessage());
                    ResponseUtil.sendError(response, ErrorCodes.TOKEN_EXPIRED);
                }
            } else {
                log.error("토큰을 찾을 수 없습니다.");
                ResponseUtil.sendError(response, ErrorCodes.TOKEN_MISSING);
            }
        }
    }
}
