package com.dq.aquaranth.login.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisKeys {
    COMPANY("COMPANY"), DEPT("DEPT"), EMP("EMP"), MENU_ROLE("MENU_ROLE");

    private final String key;
}
