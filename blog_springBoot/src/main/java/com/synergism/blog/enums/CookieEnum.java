package com.synergism.blog.enums;

public enum CookieEnum {
    JSESSIONID,PUBLIC_KEY;

    public static String JSESSIONID(){
        return JSESSIONID.name();
    }

    public static String PUBLIC_KEY(){
        return PUBLIC_KEY.name();
    }

}
