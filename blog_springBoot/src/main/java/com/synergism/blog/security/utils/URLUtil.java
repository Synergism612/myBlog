package com.synergism.blog.security.utils;

public class URLUtil {

    private static final String publicKeyPath = "/api/public/key";
    private static final String errorPath = "/error";



    public static Boolean checkURLIfToPublic(String url){
        return url.equals(publicKeyPath);
    }

    public static Boolean checkURLIfToError(String url){
        return url.equals(errorPath);
    }
}
