package com.zhaoyang.vert.core.shiro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义 Authentication 对象，使得subject除了携带用户的登录名外还可以携带更多信息
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@Setter
@Getter
@ToString
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 id
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色 id 集
     */
    private List<Integer> roleList;
    /**
     * 角色名称集
     */
    private List<String> roleNames;

}
