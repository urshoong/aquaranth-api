package com.dq.aquaranth.menu.util;

import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import static com.dq.aquaranth.menu.constant.RedisKeys.MENU_KEYS;


public class RedisUtil {

    private RedisUtil(){}

    public static String getMenuKey(MenuResponseDTO menuResponseDTO){
        return MENU_KEYS.getKeys() + menuResponseDTO.getMenuCode();
    }
}
