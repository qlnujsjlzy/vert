package com.zhaoyang.vert.core.base.wrapper;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@AllArgsConstructor
public abstract class BaseControllerWrapper {

    public Object obj = null;

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.obj instanceof List) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) this.obj;
            for (Map<String, Object> map : list) {
                wrapTheMap(map);
            }
            return list;
        } else if (this.obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.obj;
            wrapTheMap(map);
            return map;
        } else {
            return this.obj;
        }
    }

    /**
     * 包装 map 添加 name
     */
    protected abstract void wrapTheMap(Map<String, Object> map);
}
