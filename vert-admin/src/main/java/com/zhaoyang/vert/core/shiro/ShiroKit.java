package com.zhaoyang.vert.core.shiro;

import com.zhaoyang.vert.core.util.ToolUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;


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
    private static final String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 循环次数
     */
    private static final int HASH_ITERATIONS = 1024;


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
}
