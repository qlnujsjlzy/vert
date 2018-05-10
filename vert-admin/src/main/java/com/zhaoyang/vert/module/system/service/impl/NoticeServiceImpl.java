package com.zhaoyang.vert.module.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhaoyang.vert.module.system.dao.NoticeMapper;
import com.zhaoyang.vert.module.system.model.Notice;
import com.zhaoyang.vert.module.system.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通知表 服务实现类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }
}
