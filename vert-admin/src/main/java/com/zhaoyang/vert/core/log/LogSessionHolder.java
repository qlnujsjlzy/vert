package com.zhaoyang.vert.core.log;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 被修改的 bean 临时存放的地方
 * <p>
 * 1、Scope：session； 数据缓存在request上下文环境中，习惯称为线程级缓存
 * 2、proxyMode这个参数,是为了解决依存的会话或者请求上下文环境还没有时，自动装载组件报错，这里交给JDK代理,可以保证环境准备就绪时再执行组件装载
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
@Setter
@Getter
public class LogSessionHolder {

    private Object object = null;

}
