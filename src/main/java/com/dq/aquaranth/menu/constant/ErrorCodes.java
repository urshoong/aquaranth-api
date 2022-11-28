package com.dq.aquaranth.menu.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "메뉴에 대한 접근권한이 없습니다.",1L),
    TOKEN_EXPIRED(UNAUTHORIZED, "토큰이 만료되었습니다.",2L),
    INVALID_USER(UNAUTHORIZED, "아이디 또는 비밀번호를 확인해주세요.",3L),
    MENU_NOT_FOUND(NOT_FOUND, "메뉴를 찾을 수 없습니다.",1L),
    TOKEN_MISSING(UNAUTHORIZED, "access_token이 존재하지 않습니다.", 2L)
    ;

    private final HttpStatus httpStatus;
    private final String detail;
    private final Long detailErrorCode;
}
