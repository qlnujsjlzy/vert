package com.zhaoyang.vert.module.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhaoyang.vert.module.system.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(String condition);
}
