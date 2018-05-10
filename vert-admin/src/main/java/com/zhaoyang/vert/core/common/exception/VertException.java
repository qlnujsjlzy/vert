package com.zhaoyang.vert.core.common.exception;

import com.zhaoyang.vert.core.base.InterfaceEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 封装异常类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Getter
@Setter
public class VertException extends RuntimeException {

    private Integer code;

    private String message;

    public VertException(InterfaceEnum interfaceEnum) {
        this.code = interfaceEnum.getCode();
        this.message = interfaceEnum.getMessage();
    }

}
