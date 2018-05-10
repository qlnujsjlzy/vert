package com.zhaoyang.vert.core.util;

import javax.servlet.http.HttpSession;

/**
 * 非Controller中获取当前session的工具类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public class HttpSessionHolder {

    private static ThreadLocal<HttpSession> tl = new ThreadLocal<HttpSession>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
