package com.dq.aquaranth.menu.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuCode {
    String value();
}
