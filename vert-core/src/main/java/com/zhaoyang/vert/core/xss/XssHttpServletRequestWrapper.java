package com.zhaoyang.vert.core.xss;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 重写方法
 * getParameterValues(...)
 * getParameter(...)
 * getHeader(...)
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        int count = values.length;

        String[] encodeValues = new String[count];

        for (int i = 0; i < count; i++) {
            encodeValues[i] = cleanXss(values[i]);
        }
        return encodeValues;
    }

    @Override
    public String getParameter(String parameter) {
        return cleanXss(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return cleanXss(super.getHeader(name));
    }


    private String cleanXss(String value) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        //You'll need to remove the spaces from the html entities below

        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");

        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");

        value = value.replaceAll("'", "& #39;");

        value = value.replaceAll("eval\\((.*)\\)", "");

        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("script", "");

        return value;
    }

}
