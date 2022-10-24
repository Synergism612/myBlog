package com.synergism.blog.utils;

import com.synergism.blog.exception.custom.TimeFailureException;

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
     */
    public static Date toDate(String time) {
        try {
            return new SimpleDateFormat(format).parse(time);
        }catch (ParseException e){
            throw new TimeFailureException("时间转换错误");
        }
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
     * 至今为止
     * 用于求得所给时间到现在的时间
     * 返回的值根据距今时间变化，最多返回年月日
     * @param time 时间
     * @return [*年*月*日]
     */
    public static String upToNow(Date time){
        String result = "";
        long s = timeDifference(time,new Date());
        long year = years(1);
        long moon = moons(1);
        long day = days(1);
        if (s>=year){
            result+=s/year+"年";
            s = s%year;
        }
        if (s>=moon){
            result+=s/moon+"月";
            s = s%moon;
        }
        if (s>=day){
            result+=s/day+"日";
        }
        return result;
    }

    /**
     * 是否超时
     * 结束时间减起始时间是否超过秒数
     * @param start 起始时间
     * @param end 结束时间
     * @param second 秒数
     * @return 超时为真，反之为假
     */
    public static boolean isTimeOut(Date start,Date end,int second){
        return timeDifference(start, end)>second;
    }

    /**
     * 分钟转秒
     * @param minutes 分钟数
     * @return 秒数
     */
    public static long minutes(long minutes){
        return minutes*60;
    }

    /**
     * 小时转秒
     * @param hours 小时数
     * @return 秒数
     */
    public static long hours(long hours){
        return minutes(hours*60);
    }

    /**
     * 天数转秒
     * @param days 天数
     * @return 秒数
     */
    public static long days(long days){
        return hours(days*24);
    }

    /**
     * 周数转秒
     * @param weeks 周数
     * @return 秒数
     */
    public static long weeks(long weeks){
        return days(weeks*7);
    }

    /**
     * 月数转秒
     * @param moons 月数
     * @return 秒数
     */
    public static long moons(long moons){
        return days(moons*30);
    }

    /**
     * 年数转秒
     * @param years 年数
     * @return 秒数
     */
    public static long years(long years){
        return days(years*365);
    }
}
