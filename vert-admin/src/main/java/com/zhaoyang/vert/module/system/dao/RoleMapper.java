package com.zhaoyang.vert.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.module.system.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表 Mapper 接口
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据条件查询角色列表
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     */
    int deleteRolesById(@Param("roleId") Integer roleId);

    /**
     * 获取角色列表树
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);
}