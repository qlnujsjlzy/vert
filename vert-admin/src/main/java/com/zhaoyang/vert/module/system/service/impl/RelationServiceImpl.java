package com.zhaoyang.vert.module.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhaoyang.vert.module.system.dao.RelationMapper;
import com.zhaoyang.vert.module.system.model.Relation;
import com.zhaoyang.vert.module.system.service.RelationService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表 服务实现类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements RelationService {

}
