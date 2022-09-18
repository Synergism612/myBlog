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

    public static Date toDate(String time) throws ParseException {
        return new SimpleDateFormat(format).parse(time);
    }
}
