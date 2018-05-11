package com.zhaoyang.vert.core.common.constant.dictmap;

import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 通知的映射
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public class NoticeMap extends AbstractDictMap {

    @Override
    public void init() {
        put("title", "标题");
        put("content", "内容");
    }

    @Override
    protected void initBeWrapped() {
    }
}
