package com.synergism.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtil {

    static final String format = "YYYY-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     * @return 标准格式化后的时间字符串
     */
    public static String now(){
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 字符串时间转Date类型
     * @param time 字符串时间
     * @return Date类型时间
     * @throws ParseException 转换异常
     */
    public static Date toDate(String time) throws ParseException {
        return new SimpleDateFormat(format).parse(time);
    }

    /**
     * 求时间差
     * @param start 起始时间
     * @param end 结束时间
     * @return 时间差秒数
     */
    public static int timeDifference(Date start,Date end){
        return ((int)(end.getTime()-start.getTime()))/1000;
    }

    /**
     * 是否超时
     * 结束时间减起始时间是否超过秒数
     * @param start 起始时间
     * @param end 结束时间
     * @param second 秒数
     * @return 超时为真，反之为假
     */
    public static boolean ifTimeOut(Date start,Date end,int second){
        return timeDifference(start, end)>second;
    }
}
