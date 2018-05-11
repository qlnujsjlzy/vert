package com.zhaoyang.vert.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/6
 */
@Slf4j
public class ToolUtil {

    /**
     * 获取随机位数的字符串
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取异常的具体信息
     */
    public static String getExceptionMsg(Exception e) {
        if (e == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                log.error("StringWriter close error", e1);
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }


}
