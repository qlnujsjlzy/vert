package com.zhaoyang.vert.config.web;

import com.zhaoyang.vert.config.ext.BeetlExtConfig;
import com.zhaoyang.vert.config.properties.BeetlProperties;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * beetl 配置类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Configuration
public class BeetlConfig {

    @Resource
    private BeetlProperties beetlProperties;

    /**
     * beetl的配置
     */
    @Bean(initMethod = "init")
    public BeetlExtConfig beetlConfiguration() {
        BeetlExtConfig beetlConfiguration = new BeetlExtConfig();
        //TODO 后续处理 prefix
        beetlConfiguration.setResourceLoader(new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(), "/view"));
        beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
        return beetlConfiguration;
    }

    /**
     * beetl的视图解析器
     */
    @Bean
    public BeetlSpringViewResolver beetlViewResolver() {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlConfiguration());
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        return beetlSpringViewResolver;
    }
}
