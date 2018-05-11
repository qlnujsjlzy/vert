package com.zhaoyang.vert.core.base.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhaoyang.vert.core.base.tips.SuccessTip;
import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.core.page.PageInfoBT;
import com.zhaoyang.vert.core.support.HttpKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }


    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }
    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }


    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object wrapObject(BaseControllerWrapper wrapper) {
        return wrapper.warp();
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }

}
