package com.synergism.blog.util;

import com.synergism.blog.enums.TimeEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String now(){
        return new SimpleDateFormat(TimeEnum.format()).format(new Date());
    }
}
