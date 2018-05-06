package com.zhaoyang.vert.core.aop;

import com.zhaoyang.vert.core.common.annotion.BussinessLog;
import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.util.Contrast;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Aspect
@Component
@Slf4j
public class LogAop {


    @Autowired
    private PrototypeBeanInstance prototypeBeanInstance;


    private static String[] UPDATA = {"修改", "编辑"};

    @Pointcut(value = "@annotation(com.zhaoyang.vert.core.common.annotion.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        //先执行业务
        Object result = point.proceed();


        return result;
    }


    private void handle(ProceedingJoinPoint point) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        //获取拦截的方法名
        Signature signature = point.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用在方法");
        }

        MethodSignature msig = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        //TODO shiro 获取登录用户


        //获取拦截方法的参数
        String className = target.getClass().getName();
        Object[] params = point.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param).append(" & ");
        }
        log.info("请求方法的参数 params={}", sb.toString());

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class<? extends AbstractDictMap> dictClass = annotation.dict();

        if (StringUtils.containsAny(bussinessName, UPDATA)) {
            //TODO 涉及到修改，对比变化
            Object obj1 = prototypeBeanInstance.getLogSessionHolder().getObject();

        } else {
            //TODO 请求参数待处理
            Map<String, String> parameters = null;
            AbstractDictMap dictMap = dictClass.newInstance();
            Contrast.parseMultipleKey(dictMap, key, parameters);
        }

    }


}
