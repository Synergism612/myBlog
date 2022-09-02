package com.synergism.blog.util;

import java.util.Base64;

/**
 * Base64编码工具类
 */
public class Base64Util {

    public static <V> String encode(V src){
        return new String(Base64.getEncoder().encode(src.toString().getBytes()));
    }

    public static <V> String decode(V src){
        return new String(Base64.getDecoder().decode(src.toString().getBytes()));
    }

}
