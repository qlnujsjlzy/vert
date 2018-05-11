package com.zhaoyang.vert.core.datascope;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Setter
@Getter
public class DataScope {

    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_Id";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds;

    public DataScope(List<Integer> deptIds) {
        this.deptIds = deptIds;
    }
}
