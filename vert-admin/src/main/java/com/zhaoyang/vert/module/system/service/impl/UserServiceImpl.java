package com.zhaoyang.vert.module.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhaoyang.vert.core.datascope.DataScope;
import com.zhaoyang.vert.module.system.dao.UserMapper;
import com.zhaoyang.vert.module.system.model.User;
import com.zhaoyang.vert.module.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 管理员表 服务实现类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public int setStatus(Integer userId, int status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }

    @Override
    public List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, Integer deptid) {
        return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
    }

    @Override
    public int setRoles(Integer userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }
}
