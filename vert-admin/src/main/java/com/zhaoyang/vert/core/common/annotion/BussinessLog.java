package com.zhaoyang.vert.core.common.annotion;

import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;
import com.zhaoyang.vert.core.common.constant.dictmap.base.SystemDict;

import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLog {

    /**
     * 业务名称，如：“修改菜单”
     */
    String value() default "";

    /**
     * 被修改实体的唯一标识，如：菜单实体的唯一标识为 “id”
     */
    String key() default "id";

    /**
     * 字典（用于查找key的中文名称和字段的中文名称）
     */
    Class<? extends AbstractDictMap> dict() default SystemDict.class;

}
