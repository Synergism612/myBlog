package com.synergism.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.synergism.blog.enums.TimeEnum.format;

/**
 * 时间工具类
 */
public class TimeUtil {

    /**
     * 获取当前时间
     * @return 标准格式化后的时间字符串
     */
    public static String now(){
        return new SimpleDateFormat(format()).format(new Date());
    }
}
