package com.synergism.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.synergism.blog.enums.TimeEnum.format;

public class TimeUtil {
    public static String now(){
        return new SimpleDateFormat(format()).format(new Date());
    }
}
