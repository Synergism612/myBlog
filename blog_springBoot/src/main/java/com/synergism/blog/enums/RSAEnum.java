package com.synergism.blog.enums;

/**
 * 存放RSA密钥对的键名
 */
public enum RSAEnum {
    PUBLIC_KEY, PRIVATE_KEY;

    public static String PUBLIC_KEY(){
        return PUBLIC_KEY.name();
    }

    public static String PRIVATE_KEY(){
        return PRIVATE_KEY.name();
    }
}
