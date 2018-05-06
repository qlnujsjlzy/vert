package com.zhaoyang.vert;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * jar 启动
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
public class VertServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VertApplication.class);
    }
}
