package com.zhaoyang.vert.module.system.wrapper;

import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.module.system.factory.DaoFactory;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWrapper extends BaseControllerWrapper {

    public UserWrapper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        map.put("sexName", DaoFactory.instance().getSexName((Integer) map.get("sex")));
        map.put("roleName", DaoFactory.instance().getRoleName((String) map.get("roleId")));
        map.put("deptName", DaoFactory.instance().getDeptName((Integer) map.get("deptId")));
        map.put("statusName", DaoFactory.instance().getStatusName((Integer) map.get("status")));
    }

}
