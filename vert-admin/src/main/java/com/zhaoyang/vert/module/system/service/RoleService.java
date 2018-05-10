package com.zhaoyang.vert.module.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.module.system.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色相关业务
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface RoleService extends IService<Role> {

    /**
     * 设置某个角色的权限
     */
    void setAuthority(Integer roleId, String ids);

    /**
     * 删除角色
     */
    void delRoleById(Integer roleId);

    /**
     * 根据条件查询角色列表
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 删除某个角色的所有权限
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
