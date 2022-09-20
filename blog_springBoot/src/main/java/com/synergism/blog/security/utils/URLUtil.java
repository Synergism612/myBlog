package com.synergism.blog.security.utils;

import com.synergism.blog.exception.custom.IllegalRequestException;

public class URLUtil {

    private static final String[] publicPath = {"/api/public/key","/api/public/key/error"};
    private static final String[] errorPath = {"/error","/api/public/key/error"};

    /**
     * 检查uri是否去往共有接口
     * @param uri uri
     * @return 是为真，否为假
     */
    public static boolean checkURLIfToPublic(String uri){
        boolean result = false;
        for (String s : publicPath) {
            if (uri.equals(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 检查url是否前往error
     * @param uri uri
     * @return 是为真，否为假
     */
    public static boolean checkURLIfToError(String uri){
        boolean result = false;
        for (String s : errorPath) {
            if (uri.equals(s)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 检查访问权限
     * @param uri uri
     * @param powers 权限
     */
    public static void checkURLIsPower(String uri ,String[] powers){
        boolean ok = false;
        for (String power : powers) {
            if (uri.equals(power)) {
                ok = true;
                break;
            }
        }
        if (!ok){
            throw new IllegalRequestException("权限不足");
        }
    }
}
