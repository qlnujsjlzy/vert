package com.zhaoyang.vert.core.common.constant.enums;

import com.zhaoyang.vert.core.enums.InterfaceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单的状态
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@AllArgsConstructor
@Getter
public enum MenuStatusEnum implements InterfaceEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    Integer code;
    String message;

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        }
        for (MenuStatusEnum ms : MenuStatusEnum.values()) {
            if (value.equals(ms.getCode())) {
                return ms.getMessage();
            }
        }
        return "";
    }
}
