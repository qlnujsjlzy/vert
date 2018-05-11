package com.zhaoyang.vert.module.system.wrapper;

import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.module.system.factory.DaoFactory;

import java.util.List;
import java.util.Map;

/**
 * 角色列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日10:59:02
 */
public class RoleWrapper extends BaseControllerWrapper {

    public RoleWrapper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        map.put("pName", DaoFactory.instance().getSingleRoleName((Integer) map.get("pId")));
        map.put("deptName", DaoFactory.instance().getDeptName((Integer) map.get("deptId")));
    }

}
