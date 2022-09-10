package com.synergism.blog.security.utils;

import com.synergism.blog.exception.custom.IllegalRequestException;

public class URLUtil {

    private static final String publicKeyPath = "/api/public/key";
    private static final String errorPath = "/error";



    public static Boolean checkURLIfToPublic(String url){
        return url.equals(publicKeyPath);
    }

    public static Boolean checkURLIfToError(String url){
        return url.equals(errorPath);
    }

    public static void checkURLIsPower(String url ,String[] powers){
        Boolean ok = false;
        for (String power : powers) {
            if (url.contains(power)) ok = true;
        }
        if (!ok){
            throw new IllegalRequestException("权限不足");
        }
    }
}
