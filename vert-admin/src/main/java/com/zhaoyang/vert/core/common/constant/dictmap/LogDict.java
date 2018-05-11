package com.zhaoyang.vert.core.common.constant.dictmap;

import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 日志的字典
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("tips", "备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
