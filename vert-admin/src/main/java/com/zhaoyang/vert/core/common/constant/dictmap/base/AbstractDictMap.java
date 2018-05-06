package com.zhaoyang.vert.core.common.constant.dictmap.base;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * 字典映射抽象类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
public abstract class AbstractDictMap {

    private HashMap<String, String> dictMap = Maps.newHashMap();
    private HashMap<String, String> fieldWrapperDictMap = Maps.newHashMap();

    public AbstractDictMap() {
        //首项
        putDict("id", "主键id");
        init();
        initNeedWrapper();
    }

    /**
     * 初始化字段英文名称和中文名称对应的字典
     */
    public abstract void init();

    /**
     * 初始化 需要被包装的字段（如：性别 1：男；2：女）需要被包装成汉字展示
     */
    public abstract void initNeedWrapper();

    public String getDict(String key) {
        return dictMap.get(key);
    }

    public void putDict(String key, String value) {
        dictMap.put(key, value);
    }


    public String getFieldWrapperDictName(String key) {
        return fieldWrapperDictMap.get(key);
    }

    public void putFieldWrapperDict(String key, String value) {
        fieldWrapperDictMap.put(key, value);
    }

}
