package com.zhaoyang.vert.core.support;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
public class HttpKit {


    /**
     * 获取 经过 防 XSS、SQL 注入的 HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new WafRequestWrapper(request);
    }
}
