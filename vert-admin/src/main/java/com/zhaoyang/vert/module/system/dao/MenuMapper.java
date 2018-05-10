package com.zhaoyang.vert.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhaoyang.vert.core.node.MenuNode;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.module.system.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单表 Mapper 接口
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据条件查询菜单
     */
    List<Map<String, Object>> selectMenus(@Param("condition") String condition, @Param("level") String level);

    /**
     * 根据条件查询菜单
     */
    List<Long> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 获取菜单列表树
     */
    List<ZTreeNode> menuTreeList();

    /**
     * 获取菜单列表树
     */
    List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds);

    /**
     * 删除menu关联的relation
     */
    int deleteRelationByMenu(Long menuId);

    /**
     * 获取资源url通过角色id
     */
    List<String> getResUrlsByRoleId(Integer roleId);

    /**
     * 根据角色获取菜单
     */
    List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);
}