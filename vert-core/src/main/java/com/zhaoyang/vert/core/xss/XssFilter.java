package com.zhaoyang.vert.core.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * xss 过滤类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/5
 */
public class XssFilter implements Filter {

    private List<String> urlExclusion = null;
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String servletPath = httpServletRequest.getServletPath();

        if (urlExclusion != null && urlExclusion.contains(servletPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }
}
