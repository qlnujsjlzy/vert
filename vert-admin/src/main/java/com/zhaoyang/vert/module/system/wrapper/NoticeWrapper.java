package com.zhaoyang.vert.module.system.wrapper;

import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.module.system.factory.DaoFactory;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BaseControllerWrapper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", DaoFactory.instance().getUserNameById(creater));
    }

}
