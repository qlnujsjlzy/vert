package com.zhaoyang.vert.core.enums;

/**
 * 枚举抽象接口
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface InterfaceEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
