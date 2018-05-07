package com.zhaoyang.vert.core.log;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 日志 管理器
 *
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
public class LogManager {

    /**
     * 日志记录操作延时
     */
    private final int OPERATE_DELAY_MILLISECONDS = 10;
    private final String THREAD_NAME = "log-schedule";

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new CustomizableThreadFactory(THREAD_NAME));

    private LogManager() {
    }
    private static LogManager logManager = new LogManager();

    public static LogManager instance() {
        return logManager;
    }

    public void executeLog(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

}
