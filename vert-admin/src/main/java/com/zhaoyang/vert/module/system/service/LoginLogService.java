package com.zhaoyang.vert.module.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhaoyang.vert.module.system.model.LoginLog;

import java.util.List;
import java.util.Map;

/**
 * 登录记录 服务类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 获取登录日志列表
     */
    List<Map<String, Object>> getLoginLogs(Page<LoginLog> page, String beginTime, String endTime, String logName, String orderByField, boolean asc);
}
