package com.zhaoyang.vert.core.support;

import com.google.common.collect.Maps;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
public class HttpKit {

    //TODO
    public static String getIp() {
        return HttpKit.getRequest().getRemoteHost();
    }


    /**
     * 获取 经过 防 XSS、SQL 注入的 HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new WafRequestWrapper(request);
    }

    /**
     * 获取所有请求的值
     */
    public static Map<String, String> getRequestParameters() {
        Map<String, String> values = Maps.newHashMap();
        HttpServletRequest request = HttpKit.getRequest();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
    }

}
