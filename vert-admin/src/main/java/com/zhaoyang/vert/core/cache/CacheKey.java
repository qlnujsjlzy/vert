package com.zhaoyang.vert.core.cache;

/**
 * 缓存标识前缀集合
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public interface CacheKey {
    /**
     * 常量缓存
     */
    String CONSTANT = "CONSTANT";


    /**
     * 角色名称(多个)
     */
    String ROLES_NAME = "roles_name_";

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";

    /**
     * 部门名称
     */
    String DEPT_NAME = "dept_name_";

}