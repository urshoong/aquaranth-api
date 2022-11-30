package com.dq.aquaranth.menu.annotation;


import com.dq.aquaranth.menu.constant.Menu;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuCode {
    Menu value() default Menu.ROOT;
}
