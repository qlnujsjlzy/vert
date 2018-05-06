package com.zhaoyang.vert.core.base.controller;

import com.zhaoyang.vert.core.base.tips.SuccessTip;

/**
 * controller 基类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
public class BaseController {

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();



}
