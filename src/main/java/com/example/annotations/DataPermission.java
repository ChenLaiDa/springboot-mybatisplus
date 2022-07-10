package com.example.annotations;

import com.example.config.dataScope.DataScope;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:12
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

    DataScope scope() default DataScope.SUB_ALL_DEPT;
}
