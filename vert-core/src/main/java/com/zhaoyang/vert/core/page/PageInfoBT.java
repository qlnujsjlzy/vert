package com.zhaoyang.vert.core.page;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@Setter
@Getter
@ToString
public class PageInfoBT<T> {

    /**
     * 结果集
     */
    private List<T> rows;

    /**
     * 总数
     */
    private long total;

    public PageInfoBT(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

}
