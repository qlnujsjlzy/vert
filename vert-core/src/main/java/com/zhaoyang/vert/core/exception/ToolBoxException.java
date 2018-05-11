package com.zhaoyang.vert.core.exception;

import com.zhaoyang.vert.core.support.StrKit;

/**
 * 工具类初始化异常
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public class ToolBoxException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ToolBoxException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ToolBoxException(String message) {
        super(message);
    }

    public ToolBoxException(String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params));
    }

    public ToolBoxException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ToolBoxException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrKit.format(messageTemplate, params), throwable);
    }
}