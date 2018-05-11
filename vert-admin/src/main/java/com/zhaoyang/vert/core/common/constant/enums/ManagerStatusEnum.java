package com.zhaoyang.vert.core.common.constant.enums;

import com.zhaoyang.vert.core.enums.InterfaceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理状态
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@Getter
@AllArgsConstructor
public enum ManagerStatusEnum implements InterfaceEnum {

    OK(1, "启用"),
    FREEZE(2, "冻结"),
    DELETED(3, "被删除");

    Integer code;
    String message;

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        }
        for (ManagerStatusEnum ms : ManagerStatusEnum.values()) {
            if (value.equals(ms.getCode())) {
                return ms.getMessage();
            }
        }
        return "";
    }

}
