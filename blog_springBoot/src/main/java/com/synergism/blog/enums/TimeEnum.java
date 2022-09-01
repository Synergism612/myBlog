package com.synergism.blog.enums;

/**
 * 用于存放一些有关时间的值
 */
public enum TimeEnum {

    TIME,DAY,MONTH,YEAR,HOUR,MINUTE,SECOND,
    time,day,month,year,hour,minute,second;

    private static String format = "YYYY-MM-dd HH:mm:ss";

    public static String format(){
        return format;
    }

}
