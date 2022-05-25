package com.example.annotations;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/12/15 16:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZjHxLog {
    String value() default "";
    //1-增删改，2-查询，默认是1
    int type() default 1;
}
