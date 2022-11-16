package com.dq.aquaranth.menu.exception;

import com.dq.aquaranth.menu.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuException extends RuntimeException{
    private final ErrorCode errorCode;
}
