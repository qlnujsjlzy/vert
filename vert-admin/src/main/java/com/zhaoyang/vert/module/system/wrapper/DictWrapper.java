package com.zhaoyang.vert.module.system.wrapper;

import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import com.zhaoyang.vert.module.system.model.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DictWrapper extends BaseControllerWrapper {

    public DictWrapper(Object list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Integer id = (Integer) map.get("id");
        List<Dict> dicts = DaoFactory.instance().findInDict(id);
        if(dicts != null){
            for (Dict dict : dicts) {
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
    }

}
