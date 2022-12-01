package com.dq.aquaranth.menu.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisKeys {
    ROLES_KEYS("R_"),
    MENU_KEYS("M_");

    private final String keys;
}
