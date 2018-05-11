package com.zhaoyang.vert.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 *
 */
@AllArgsConstructor
@Getter
public enum VertExceptionEnum implements InterfaceEnum {

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求有错误"),
    SERVER_ERROR(500, "服务器异常");


    private Integer code;

    private String message;


}
