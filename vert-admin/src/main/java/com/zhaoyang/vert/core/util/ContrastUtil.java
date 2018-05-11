package com.zhaoyang.vert.core.util;

import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;
import com.zhaoyang.vert.core.support.StrKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 对比两个对象的变化
 *
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Slf4j
public class ContrastUtil {

    private static String COMMA_SEPARATOR = ",";

    /**
     * 记录每个修改字段的分隔符
     */
    public static final String SEPARATOR = ";;;";


    public static String contrastObj(Class dictClass, String key, Object pojo1, Map<String, String> pojo2) throws IllegalAccessException, InstantiationException {
        AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
        StringBuilder sb = new StringBuilder(parseMultipleKey(dictMap, key, pojo2) + SEPARATOR);
        try {
            Class<?> clazz = pojo1.getClass();
            Field[] fields = clazz.getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = pojo2.get(StrKit.firstCharToLowerCase(getMethod.getName().substring(3)));
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (o1 instanceof Date) {
                    o1 = DateUtil.getDay((Date) o1);
                } else if (o1 instanceof Integer) {
                    o2 = Integer.parseInt(o2.toString());
                }
                if (!o1.toString().equals(o2.toString())) {
                    if (i != 1) {
                        sb.append(SEPARATOR);
                    }
                    String fieldName = dictMap.get(field.getName());
                    String fieldWrapperDictName = dictMap.getFieldWrapperMethodName(field.getName());
                    //TODO 字段比较
                    if (fieldWrapperDictName != null) {

                    } else {
                        sb.append("字段名称:").append(fieldName).append(",旧值:").append(o1).append(",新值:").append(o2);
                    }
                    i++;
                }
            }

        } catch (Exception e) {
            log.error("contrastObj error", e);
        }

        return sb.toString();
    }


    /**
     * 解析多个 key（逗号分隔的）
     */
    public static String parseMultipleKey(AbstractDictMap dictMap, String key, Map<String, String> requests) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.contains(key, COMMA_SEPARATOR)) {
            String[] keys = key.split(COMMA_SEPARATOR);
            for (String item : keys) {
                appendLog(sb, item, dictMap, requests);
            }
            String log = sb.toString();
            return StringUtils.substring(log, 0, log.length() - 1);
        }
        return appendLog(sb, key, dictMap, requests);

    }

    private static String appendLog(StringBuilder sb, String item, AbstractDictMap dictMap, Map<String, String> requests) {
        String fieldWrapperDictName = dictMap.getFieldWrapperMethodName(item);
        String value = requests.get(item);
        if (StringUtils.isNotBlank(fieldWrapperDictName)) {
            //TODO 字典表字段,更新 value
            sb.append(dictMap.get(item)).append("=").append(value).append(",");
        } else {
            sb.append(dictMap.get(item)).append("=").append(value).append(",");
        }
        return sb.toString();
    }

}
