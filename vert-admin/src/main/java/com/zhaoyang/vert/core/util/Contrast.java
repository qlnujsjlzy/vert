package com.zhaoyang.vert.core.util;

import com.zhaoyang.vert.core.common.constant.dictmap.base.AbstractDictMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
public class Contrast {

    private static String COMMA_SEPARATOR = ",";

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
        String fieldWrapperDictName = dictMap.getFieldWrapperDictName(item);
        String value = requests.get(item);
        if (StringUtils.isNotBlank(fieldWrapperDictName)) {
            //TODO 字典表字段,更新 value
            sb.append(dictMap.getDict(item)).append("=").append(value).append(",");
        } else {
            sb.append(dictMap.getDict(item)).append("=").append(value).append(",");
        }
        return sb.toString();
    }

}
