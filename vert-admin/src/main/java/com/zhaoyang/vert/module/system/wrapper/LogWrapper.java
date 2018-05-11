package com.zhaoyang.vert.module.system.wrapper;


import com.zhaoyang.vert.core.base.wrapper.BaseControllerWrapper;
import com.zhaoyang.vert.core.util.ContrastUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 日志列表的包装类
 *
 * @author fengshuonan
 * @date 2017年4月5日22:56:24
 */
public class LogWrapper extends BaseControllerWrapper {

    public LogWrapper(Object list) {
        super(list);
    }

    @Override
    public void wrapTheMap(Map<String, Object> map) {
        String message = (String) map.get("message");

        Integer userid = (Integer) map.get("userid");
        map.put("userName", DaoFactory.instance().getUserNameById(userid));

        //如果信息过长,则只截取前100位字符串
        if (StringUtils.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put("message", subMessage);
        }

        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (StringUtils.isNotEmpty(message) && message.contains(ContrastUtil.SEPARATOR)) {
            String[] msgs = message.split(ContrastUtil.SEPARATOR);
            map.put("regularMessage",msgs);
        }else{
            map.put("regularMessage",message);
        }
    }

}
