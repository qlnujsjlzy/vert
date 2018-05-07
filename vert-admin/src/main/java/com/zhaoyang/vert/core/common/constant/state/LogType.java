package com.zhaoyang.vert.core.common.constant.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志类型
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@AllArgsConstructor
@Getter
public enum LogType {


    LOGIN("登录日志"),
    LOGIN_FAIL("登录失败日志"),
    EXIT("退出日志"),
    EXCEPTION("异常日志"),
    BUSSINESS("业务日志");

    String message;

}
