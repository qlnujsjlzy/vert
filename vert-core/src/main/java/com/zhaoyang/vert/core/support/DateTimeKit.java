package com.zhaoyang.vert.core.support;


import com.zhaoyang.vert.core.exception.ToolBoxException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
public class DateTimeKit {


    /** 标准日期格式 */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /** 标准时间格式 */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /** 标准日期时间格式，精确到分 */
    public final static String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    /** 标准日期时间格式，精确到秒 */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 标准日期时间格式，精确到毫秒 */
    public final static String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /** HTTP头中日期时间格式 */
    public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";


    /** 标准日期（不含时间）格式化器 */
    private static ThreadLocal<SimpleDateFormat> NORM_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        synchronized protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(NORM_DATE_PATTERN);
        };
    };
    /** 标准时间格式化器 */
    private static ThreadLocal<SimpleDateFormat> NORM_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        synchronized protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(NORM_TIME_PATTERN);
        };
    };
    /** 标准日期时间格式化器 */
    private static ThreadLocal<SimpleDateFormat> NORM_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        synchronized protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(NORM_DATETIME_PATTERN);
        };
    };
    /** HTTP日期时间格式化器 */
    private static ThreadLocal<SimpleDateFormat> HTTP_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
        @Override
        synchronized protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
        };
    };

    // ------------------------------------ Format start ----------------------------------------------
    /**
     * 根据特定格式格式化日期
     *
     * @param date 被格式化的日期
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        if(null == date){
            return null;
        }
        return NORM_DATETIME_FORMAT.get().format(date);
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        if(null == date){
            return null;
        }
        return NORM_DATE_FORMAT.get().format(date);
    }

    /**
     * 格式化为Http的标准日期格式
     *
     * @param date 被格式化的日期
     * @return HTTP标准形式日期字符串
     */
    public static String formatHttpDate(Date date) {
        if(null == date){
            return null;
        }
        return HTTP_DATETIME_FORMAT.get().format(date);
    }
    // ------------------------------------ Format end ----------------------------------------------

    // ------------------------------------ Parse start ----------------------------------------------

    /**
     * 构建DateTime对象
     *
     * @param dateStr Date字符串
     * @param simpleDateFormat 格式化器
     * @return DateTime对象
     */
    public static DateTime parse(String dateStr, SimpleDateFormat simpleDateFormat) {
        try {
            return new DateTime(simpleDateFormat.parse(dateStr));
        } catch (Exception e) {
            throw new ToolBoxException(StrKit.format("Parse [{}] with format [{}] error!", dateStr, simpleDateFormat.toPattern()), e);
        }
    }

    /**
     * 将特定格式的日期转换为Date对象
     *
     * @param dateString 特定格式的日期
     * @param format 格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static DateTime parse(String dateString, String format) {
        return parse(dateString, new SimpleDateFormat(format));
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static DateTime parseDateTime(String dateString) {
        return parse(dateString, NORM_DATETIME_FORMAT.get());
    }

    /**
     * 格式yyyy-MM-dd
     *
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static DateTime parseDate(String dateString) {
        return parse(dateString, NORM_DATE_FORMAT.get());
    }

    /**
     * 格式HH:mm:ss
     *
     * @param timeString 标准形式的日期字符串
     * @return 日期对象
     */
    public static DateTime parseTime(String timeString) {
        return parse(timeString, NORM_TIME_FORMAT.get());
    }

    /**
     * 格式：<br>
     * 1、yyyy-MM-dd HH:mm:ss<br>
     * 2、yyyy-MM-dd<br>
     * 3、HH:mm:ss<br>
     * 4、yyyy-MM-dd HH:mm 5、yyyy-MM-dd HH:mm:ss.SSS
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static DateTime parse(String dateStr) {
        if (null == dateStr) {
            return null;
        }
        dateStr = dateStr.trim();
        int length = dateStr.length();
        try {
            if (length == NORM_DATETIME_PATTERN.length()) {
                return parseDateTime(dateStr);
            } else if (length == NORM_DATE_PATTERN.length()) {
                return parseDate(dateStr);
            } else if (length == NORM_TIME_PATTERN.length()) {
                return parseTime(dateStr);
            } else if (length == NORM_DATETIME_MINUTE_PATTERN.length()) {
                return parse(dateStr, NORM_DATETIME_MINUTE_PATTERN);
            } else if (length >= NORM_DATETIME_MS_PATTERN.length() - 2) {
                return parse(dateStr, NORM_DATETIME_MS_PATTERN);
            }
        } catch (Exception e) {
            throw new ToolBoxException(StrKit.format("Parse [{}] with format normal error!", dateStr));
        }

        // 没有更多匹配的时间格式
        throw new ToolBoxException(StrKit.format(" [{}] format is not fit for date pattern!", dateStr));
    }
    // ------------------------------------ Parse end ----------------------------------------------


}
