package com.zhaoyang.vert.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.zhaoyang.vert.core.datascope.DataScopeInterceptor;
import com.zhaoyang.vert.core.datasource.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * Mybatis-Plus配置
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
@Configuration
@MapperScan(basePackages = {"com.zhaoyang.vert.module.*.dao"})
@EnableTransactionManagement()
public class MybatisPlusConfig {

    @Resource
    private DruidProperties druidProperties;


    /**
     * 默认单数据源配置
     */
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁 mybatis 插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    /**
     * 数据范围 mybatis 插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
