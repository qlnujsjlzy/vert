package com.zhaoyang.vert.core.support;

/**
 * 集合相关工具类，包括数组
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public class CollectionKit {

    /**
     * 以 conjunction 为分隔符将集合转换为字符串
     *
     * @param <T>        被处理的集合
     * @param collection 集合
     * @param separator  分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(Iterable<T> collection, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T item : collection) {
            sb.append(item).append(separator);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
