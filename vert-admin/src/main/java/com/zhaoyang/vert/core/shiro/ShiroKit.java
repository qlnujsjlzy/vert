package com.zhaoyang.vert.core.shiro;

import com.zhaoyang.vert.core.common.constant.Const;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;


/**
 * shiro 工具类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
public class ShiroKit {

    /**
     * 名称逗号分隔符
     */
    private static final String NAMES_COMMA_SEPARATOR = ",";

    /**
     * 加盐参数
     */
    public static final String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 循环次数
     */
    public static final int HASH_ITERATIONS = 1024;


    /**
     * shiro 密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐值
     */
    public static String md5(String credentials, String saltSource) {
        Md5Hash salt = new Md5Hash(saltSource);
        return new SimpleHash(HASH_ALGORITHM_NAME, credentials, salt, HASH_ITERATIONS).toString();
    }


    /**
     * 获取随机盐值
     *
     * @param length 获取盐值得长度
     */
    public static String getRandomSalt(int length) {
        return ToolUtil.getRandomString(length);
    }

    /**
     * 获取当前 Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    /**
     * 验证当前用户是否属于该角色？,使用时与lacksRole 搭配使用
     *
     * @param roleName
     *            角色名
     * @return 属于该角色：true，否则false
     */
    public static boolean hasRole(String roleName) {
        return getSubject() != null && roleName != null
                && roleName.length() > 0 && getSubject().hasRole(roleName);
    }

    /**
     * 获取封装的 ShiroUser
     */
    public static ShiroUser getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    /**
     * 检验当前用户是否为“访客”，即未认证（包含为记住）的用户，用User搭配使用
     *
     * @return true 访客；
     */
    private static boolean isGuest() {
        return !isUser();

    }

    /**
     * 认证通过或是记住的用户。与guest搭配使用
     *
     * @return true 用户；
     */
    private static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 判断当前用户是否是超级管理员
     */
    public static boolean isAdmin() {
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        for (Integer integer : roleList) {
            String singleRoleTip = DaoFactory.instance().getSingleRoleTip(integer);
            if (singleRoleTip.equals(Const.ADMIN_NAME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前用户的部门数据范围的集合
     */
    public static List<Integer> getDeptDataScope() {
        Integer deptId = getUser().getDeptId();
        List<Integer> subDeptIds = DaoFactory.instance().getSubDeptId(deptId);
        subDeptIds.add(deptId);
        return subDeptIds;
    }

    /**
     * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
     *
     * @return 通过身份验证：true，否则false
     */
    public static boolean isAuthenticated() {
        return getSubject() != null && getSubject().isAuthenticated();
    }

    /**
     * 从shiro获取session
     */
    public static Session getSession() {
        return getSubject().getSession();
    }
}
