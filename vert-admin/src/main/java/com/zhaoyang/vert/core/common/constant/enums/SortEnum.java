package com.zhaoyang.vert.core.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库排序
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@AllArgsConstructor
@Getter
public enum SortEnum {

    ASC("asc"),
    DESC("desc");

    String message;
}
