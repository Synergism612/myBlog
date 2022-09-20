package com.synergism.blog.security.utils;

import com.synergism.blog.exception.custom.IllegalRequestException;

public class URLUtil {

    private static final String[] publicPath = {"/api/public/key","/api/public/key/error"};
    private static final String[] errorPath = {"/error","/api/public/key/error"};



    public static boolean checkURLIfToPublic(String url){
        boolean result = false;
        for (String s : publicPath) {
            if (url.equals(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkURLIfToError(String url){
        boolean result = false;
        for (String s : errorPath) {
            if (url.equals(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void checkURLIsPower(String url ,String[] powers){
        boolean ok = false;
        for (String power : powers) {
            if (url.equals(power)) {
                ok = true;
                break;
            }
        }
        if (!ok){
            throw new IllegalRequestException("权限不足");
        }
    }
}
