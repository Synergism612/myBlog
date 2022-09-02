package com.synergism.blog.util;

/**
 * URL工具类
 */
public class URLUtil {

    /**
     * 检查URL中是否包含某路径
     * @param url url
     * @param contain 路径
     * @return 包含为真，否之为假
     */
    public static boolean checkURLContain(String url,String contain){
        return StringUtil.checkStringContain(url, contain);
    }
}
