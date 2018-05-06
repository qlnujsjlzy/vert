package com.zhaoyang.vert.core.base.tips;


import lombok.AllArgsConstructor;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@AllArgsConstructor
public class Tip {

    private int code;
    private String message;

}
