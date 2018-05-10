package com.zhaoyang.vert.module.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhaoyang.vert.module.system.dao.LoginLogMapper;
import com.zhaoyang.vert.module.system.model.LoginLog;
import com.zhaoyang.vert.module.system.service.LoginLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 登录记录 服务实现类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public List<Map<String, Object>> getLoginLogs(Page<LoginLog> page, String beginTime, String endTime, String logName, String orderByField, boolean asc) {
        return this.baseMapper.getLoginLogs(page, beginTime, endTime, logName, orderByField, asc);
    }
}
