package com.zhaoyang.vert.core.log.task;

import com.zhaoyang.vert.core.common.constant.state.LogSucceed;
import com.zhaoyang.vert.core.common.constant.state.LogType;
import com.zhaoyang.vert.module.system.model.LoginLog;
import com.zhaoyang.vert.module.system.model.OperationLog;

import java.util.Date;

/**
 * 日志对象域
 *
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
public class LogDomain {


    /*
     *创建操作日志
     */

    public static OperationLog createOperationLog(LogType logType, Integer userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogType(logType.getMessage());
        operationLog.setLogName(bussinessName);
        operationLog.setUserId(userId);
        operationLog.setClassName(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     */
    public static LoginLog createLoginLog(LogType logType, Integer userId, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogName(logType.getMessage());
        loginLog.setUserId(userId);
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
