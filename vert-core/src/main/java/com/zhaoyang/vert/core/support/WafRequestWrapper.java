package com.zhaoyang.vert.core.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Request 请求过滤包装
 * waf 防火墙
 *
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */

public class WafRequestWrapper extends HttpServletRequestWrapper {

    private boolean filterXSS = true;
    private boolean filterSQL = true;

    public WafRequestWrapper(HttpServletRequest request) {
        this(request, true, true);

    }

    public WafRequestWrapper(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {
        super(request);
        this.filterXSS = filterXSS;
        this.filterSQL = filterSQL;
    }

    //TODO 过滤逻辑
}
