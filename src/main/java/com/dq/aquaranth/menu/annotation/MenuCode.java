package com.dq.aquaranth.menu.annotation;


import com.dq.aquaranth.menu.constant.MenuCodes;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuCode {
    MenuCodes value() default MenuCodes.ROOT;
}
