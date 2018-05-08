package com.zhaoyang.vert.core.aop;

import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;
import com.zhaoyang.vert.core.log.LogManager;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.log.task.LogTask;
import com.zhaoyang.vert.core.shiro.ShiroKit;
import com.zhaoyang.vert.core.shiro.ShiroUser;
import com.zhaoyang.vert.core.support.HttpKit;
import com.zhaoyang.vert.core.util.Contrast;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 * aspect 中使用 autowired ，需要使用注解 @Configurable、@ComponentScan
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Aspect
@Component
@Configurable
@ComponentScan("com.zhaoyang.vert")
@Slf4j
public class BusinessLogAop {


    @Resource
    private PrototypeBeanInstance prototypeBeanInstance;


    private static String[] UPDATE = {"修改", "编辑"};

    @Pointcut(value = "@annotation(com.zhaoyang.vert.core.common.annotion.BusinessLog)")
    public void cutBusinessLog() {
    }

    @Around("cutBusinessLog()")
    public Object recordBusinessLog(ProceedingJoinPoint point) throws Throwable {
        //先执行业务
        Object result = point.proceed();
        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错！", e);
        }
        return result;
    }


    private void handle(ProceedingJoinPoint point) throws Exception {
        //获取拦截的方法名
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用在方法");
        }

        //如果当前用户未登录，不做日志
        ShiroUser user = ShiroKit.getUser();
        if (user == null) {
            return;
        }

        MethodSignature ms = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(ms.getName(), ms.getParameterTypes());
        String methodName = currentMethod.getName();


        //获取拦截方法的参数
        String className = target.getClass().getName();
        Object[] params = point.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param).append(" & ");
        }
        log.info("请求方法的参数 params={}", sb.toString());

        //获取操作名称
        BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
        String businessName = annotation.value();
        String key = annotation.key();
        Class<? extends AbstractDictMap> dictClass = annotation.dict();

        String msg ;
        if (StringUtils.containsAny(businessName, UPDATE)) {
            //TODO 涉及到修改，对比变化
            Object obj1 = prototypeBeanInstance.getLogSessionHolder().getObject();
            Map<String, String> obj2 = HttpKit.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            Map<String, String> parameters = HttpKit.getRequestParameters();
            AbstractDictMap dictMap = dictClass.newInstance();
            msg = Contrast.parseMultipleKey(dictMap, key, parameters);
        }

        LogManager.instance().executeLog(LogTask.businessLog(user.getId(), businessName, className, methodName, msg));

    }


}
