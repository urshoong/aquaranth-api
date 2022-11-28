package com.dq.aquaranth.menu.exception;

import com.dq.aquaranth.menu.constant.ErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MenuException extends RuntimeException{
    private final ErrorCodes errorCodes;
}
