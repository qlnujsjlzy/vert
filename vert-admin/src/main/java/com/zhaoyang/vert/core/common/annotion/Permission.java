package com.zhaoyang.vert.core.common.annotion;

import java.lang.annotation.*;

/**
 * 权限注解 用于检查权限 规定访问权限
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {


    /**
     * 角色英文名称
     * 使用注解时加上这个值，表示限制只有某个角色的才可以访问对应的资源
     * 常用在某些资源限制只有超级管理员角色才能访问
     */
    String[] value() default {};
}
