package com.zhaoyang.vert.core.common.constant.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务是否成功的日志记录
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@AllArgsConstructor
@Getter
public enum LogSucceed {

    SUCCESS("成功"),

    FAIL("失败");

    String message;

}
