package com.dq.aquaranth.login.jwt;

public interface JwtProperties {
    String SECRET = "douzone"; // 우리 서버만 알고 있는 비밀값
    Integer ACCESS_TOKEN_EXPIRATION_TIME = 999999 * 1000; // 1000 = 1초
    Integer REFRESH_TOKEN_EXPIRATION_TIME = (10 * 3600 * 24); // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
