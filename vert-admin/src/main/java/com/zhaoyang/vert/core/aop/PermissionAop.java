package com.zhaoyang.vert.core.aop;

import com.zhaoyang.vert.core.common.annotion.Permission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

/**
 * Aop 权限自定义检查
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@Aspect
@Component
@Configurable
@ComponentScan("com.zhaoyang.vert")
@Order(200)
@Slf4j
public class PermissionAop {

    @Pointcut(value = "@annotation(com.zhaoyang.vert.core.common.annotion.Permission)")
    private void cutPermission() {
    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        String[] permissions = permission.value();
        if (ArrayUtils.isEmpty(permissions)) {
            //检查全体角色 TODO 获取用户角色
            boolean result = true;
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        } else {
            //检查指定角色
            boolean result = true;
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        }
    }
}
