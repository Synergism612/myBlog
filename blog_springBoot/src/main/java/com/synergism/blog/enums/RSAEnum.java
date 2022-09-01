package com.synergism.blog.enums;

public enum RSAEnum {
    PUBLIC,PRIVATE;

    public static String PUBLIC(){
        return PUBLIC.name();
    }

    public static String PRIVATE(){
        return PRIVATE.name();
    }
}
