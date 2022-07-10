package com.example.aspect;

import com.example.annotations.DataPermission;
import com.example.config.dataScope.DataScopeContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/7/10 11:13
 */
@Slf4j
@Aspect
@Component
public class DataPermissionAspect {
    //这里需要注意了，这个是将自己自定义注解作为切点的根据，路径一定要写正确了
    @Pointcut(value = "@annotation(com.example.annotations.DataPermission)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable{
//        Map<String,DataScope> context = new HashMap<>();
        // 组装类名+方法名
//        String className = pjp.getTarget().getClass().getName();
//        String methodName = pjp.getSignature().getName();
//        String name = className + "." + methodName;

        // 获取注解值
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        DataPermission annotation = signature.getMethod().getAnnotation(DataPermission.class);
//        context.put(name, annotation.scope());
        // 将类名存到ThreadLocal中
        DataScopeContext.setDataScopeContext(annotation.scope());
        return pjp.proceed();
    }
}
