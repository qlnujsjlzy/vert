package com.zhaoyang.vert.module.system.controller;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhaoyang.vert.core.base.controller.BaseController;
import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.annotion.Permission;
import com.zhaoyang.vert.core.common.constant.Const;
import com.zhaoyang.vert.module.system.factory.PageFactory;
import com.zhaoyang.vert.module.system.model.LoginLog;
import com.zhaoyang.vert.module.system.service.LoginLogService;
import com.zhaoyang.vert.module.system.wrapper.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log.html";
    }

    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
        List<Map<String, Object>> result = loginLogService.getLoginLogs(page, beginTime, endTime, logName, page.getOrderByField(), page.isAsc());
        page.setRecords((List<LoginLog>) new LogWrapper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 清空日志
     */
    @BusinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        SqlRunner.db().delete("delete from sys_login_log");
        return SUCCESS_TIP;
    }
}
