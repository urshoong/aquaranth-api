package com.dq.aquaranth.login.jwt;

/**
 * JWT 토큰 설정
 *
 * @author 임종현
 */
public interface JwtProperties {
    String SECRET = "douzone"; // 비밀 키
    Integer ACCESS_TOKEN_EXPIRATION_TIME = 999999 * 1000; // access token 만료시간 (1000 = 1초)
    Integer REFRESH_TOKEN_EXPIRATION_TIME = (10 * 3600 * 24); // refresh token 만료시간 (10일 (1/1000초))
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
