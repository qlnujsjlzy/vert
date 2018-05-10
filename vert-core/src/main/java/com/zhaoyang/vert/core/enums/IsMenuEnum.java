package com.zhaoyang.vert.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否是菜单的枚举
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@AllArgsConstructor
@Getter
public enum IsMenuEnum {

    YES(1, "是"),
    NO(0, "不是");

    int code;
    String message;


    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        }
        for (IsMenuEnum s : IsMenuEnum.values()) {
            if (s.getCode() == status) {
                return s.getMessage();
            }
        }
        return "";
    }
}
