package com.zhaoyang.vert.core.log;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 多例的组件自动组装服务类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
public class PrototypeBeanInstance {

    @Resource
    private LogSessionHolder logSessionHolder;
}
