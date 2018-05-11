package com.zhaoyang.vert.core.common.constant.dictmap;


import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 角色的字典
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public class RoleDict extends AbstractDictMap {

    @Override
    public void init() {
        put("roleId", "角色名称");
        put("num", "角色排序");
        put("pId", "角色的父级");
        put("name", "角色名称");
        put("deptId", "部门名称");
        put("tips", "备注");
        put("ids", "资源名称");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("pid", "getSingleRoleName");
        putFieldWrapperMethodName("deptid", "getDeptName");
        putFieldWrapperMethodName("roleId", "getSingleRoleName");
        putFieldWrapperMethodName("ids", "getMenuNames");
    }
}
