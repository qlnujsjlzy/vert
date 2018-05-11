package com.zhaoyang.vert.core.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页参数类（for BootStrap Table）
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@Setter
@Getter
@ToString
public class PageBT {
    /**
     * 每页显示个数
     */
    private int limit;
    /**
     * 查询的偏移量（查询的页数 = offset/limit + 1）
     */
    private int offset;
    /**
     * 排序方式
     */
    private String order;


    public PageBT(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }
}
