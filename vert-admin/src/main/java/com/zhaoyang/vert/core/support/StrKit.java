package com.zhaoyang.vert.core.support;

/**
 * 字符串 工具类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
public class StrKit {

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }


}
