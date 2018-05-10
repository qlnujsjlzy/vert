package com.zhaoyang.vert.config.web;

import com.zhaoyang.vert.config.properties.BeetlProperties;

import javax.annotation.Resource;

/**
 * beetl 配置类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public class BeetlConfig {

    @Resource
    private BeetlProperties beetlProperties;
}
