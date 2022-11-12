package com.dq.aquaranth.menu.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "메뉴에 대한 접근권한이 없습니다."),

    MENU_NOT_FOUND(NOT_FOUND, "메뉴를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
