package com.synergism.blog.util;


import java.util.Base64;

public class base64Util {

    public static String encode(String src){
        return new String(Base64.getEncoder().encode(src.getBytes()));
    }

    public static String decode(String src){
        return new String(Base64.getDecoder().decode(src.getBytes()));
    }

}
