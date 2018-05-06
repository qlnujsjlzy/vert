package com.zhaoyang.vert.core.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 错误页面的默认跳转（例如 请求 404 的时候，默认走这个视图解析器）
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Component("error")
@Slf4j
public class VertErrorView implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }


    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.error("vert error view forward");
        //RequestDispatcher.forward  请求转发，1、浏览器地址保持初始的url地址不变；2、调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和相应过程
        //HttpServletResponse.sendRedirect 请求重定向，1、浏览器地址发生变化；2、调用者与被调用者使用各自的request对象和response对象，它们属于两个独立的访问请求和响应过程
        httpServletRequest.getRequestDispatcher("/global/error").forward(httpServletRequest, httpServletResponse);
    }
}
