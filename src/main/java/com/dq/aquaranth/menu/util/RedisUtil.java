package com.dq.aquaranth.menu.util;

import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import static com.dq.aquaranth.menu.constant.Menu.REDIS_KEYS;


public class RedisUtil {

    private RedisUtil(){}

    public static String getMenuKey(MenuResponseDTO menuResponseDTO){
        return REDIS_KEYS.getCode() + menuResponseDTO.getMenuCode();
    }
}
