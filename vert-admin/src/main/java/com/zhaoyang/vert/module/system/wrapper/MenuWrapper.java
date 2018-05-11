package com.zhaoyang.vert.module.system.wrapper;


import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.core.enums.IsMenuEnum;
import com.zhaoyang.vert.module.system.factory.DaoFactory;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class MenuWrapper extends BaseControllerWrapper {

    public MenuWrapper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        map.put("statusName", DaoFactory.instance().getMenuStatusName((Integer) map.get("status")));
        map.put("isMenuName", IsMenuEnum.valueOf((Integer) map.get("ismenu")));
    }

}
