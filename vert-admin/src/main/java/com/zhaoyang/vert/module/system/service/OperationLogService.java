package com.zhaoyang.vert.module.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhaoyang.vert.module.system.model.OperationLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 获取操作日志列表
     */
    List<Map<String, Object>> getOperationLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String s, String orderByField, boolean asc);
}
