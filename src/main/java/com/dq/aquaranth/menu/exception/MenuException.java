package com.dq.aquaranth.menu.exception;

import com.dq.aquaranth.menu.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MenuException extends RuntimeException{
    private final ErrorCode errorCode;
}
