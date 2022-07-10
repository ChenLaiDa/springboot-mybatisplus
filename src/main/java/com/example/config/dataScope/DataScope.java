package com.example.config.dataScope;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:14
 */
public enum DataScope {
    ALL("all"),  // 所有
    SUB_ALL_DEPT("subAllDept"),  // 本部门及下级部门
    SERF_DEPT("serfDept"),  // 本部门
    SERF("serf");  // 自己

    private String name;

    DataScope(String name){
        this.name = name;
    }
}
