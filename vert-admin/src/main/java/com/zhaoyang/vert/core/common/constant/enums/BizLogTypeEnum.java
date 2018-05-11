package com.zhaoyang.vert.core.common.constant.enums;

import com.zhaoyang.vert.core.enums.InterfaceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务日志类型
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@AllArgsConstructor
@Getter
public enum BizLogTypeEnum implements InterfaceEnum {

    //全部日志
    ALL(0, null),
    BUSINESS(1, "业务日志"),
    EXCEPTION(2, "异常日志");

    Integer code;
    String message;

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        }
        for (BizLogTypeEnum ms : BizLogTypeEnum.values()) {
            if (value.equals(ms.getCode())) {
                return ms.getMessage();
            }
        }
        return "";
    }
}
