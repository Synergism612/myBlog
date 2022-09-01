package com.synergism.blog.enums;

public enum TimeEnum {

    TIME,DAY,MONTH,YEAR,HOUR,MINUTE,SECOND,
    time,day,month,year,hour,minute,second;

    private static String format = "YYYY-MM-dd HH:mm:ss";

    public static String format(){
        return format;
    }

}
