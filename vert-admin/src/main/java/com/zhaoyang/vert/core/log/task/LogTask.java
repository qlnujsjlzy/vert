package com.zhaoyang.vert.core.log.task;

import com.zhaoyang.vert.core.common.constant.enums.LogSucceedEnum;
import com.zhaoyang.vert.core.common.constant.enums.LogTypeEnum;
import com.zhaoyang.vert.module.system.dao.LoginLogMapper;
import com.zhaoyang.vert.module.system.dao.OperationLogMapper;
import com.zhaoyang.vert.module.system.model.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.TimerTask;

/**
 * 日志操作任务
 *
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
@Slf4j
@Component
public class LogTask {


    private static LogTask logTask;

    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private OperationLogMapper operationLogMapper;

    @PostConstruct
    private void init() {
        logTask = this;
        logTask.loginLogMapper = this.loginLogMapper;
        logTask.operationLogMapper = this.operationLogMapper;
    }

    public static TimerTask businessLog(final Integer userId, final String businessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogDomain.createOperationLog(
                        LogTypeEnum.BUSINESS, userId, businessName, clazzName, methodName, msg, LogSucceedEnum.SUCCESS);
                try {
                    logTask.operationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    log.error("创建业务日志异常!", e);
                }
            }
        };
    }
}
