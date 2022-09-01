package com.synergism.blog.enums;

/**
 * 用于存放一些cookie的键名
 */
public enum CookieEnum {
    JSESSIONID;

    public static String JSESSIONID(){
        return JSESSIONID.name();
    }
}
