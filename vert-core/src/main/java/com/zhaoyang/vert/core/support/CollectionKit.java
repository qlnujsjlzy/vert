package com.zhaoyang.vert.core.support;


import com.zhaoyang.vert.core.exception.ToolBoxException;

import java.util.Arrays;

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

    /**
     * 判定给定对象是否为数组类型
     *
     * @param obj 对象
     * @return 是否为数组类型
     */
    public static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    /**
     * 数组或集合转String
     *
     * @param obj 集合或数组对象
     * @return 数组字符串，与集合转字符串格式相同
     */
    public static String arrayToString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (isArray(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception e) {
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return Arrays.toString((long[]) obj);
                    case "int":
                        return Arrays.toString((int[]) obj);
                    case "short":
                        return Arrays.toString((short[]) obj);
                    case "char":
                        return Arrays.toString((char[]) obj);
                    case "byte":
                        return Arrays.toString((byte[]) obj);
                    case "boolean":
                        return Arrays.toString((boolean[]) obj);
                    case "float":
                        return Arrays.toString((float[]) obj);
                    case "double":
                        return Arrays.toString((double[]) obj);
                    default:
                        throw new ToolBoxException(e);
                }
            }
        }
        return obj.toString();
    }

}
