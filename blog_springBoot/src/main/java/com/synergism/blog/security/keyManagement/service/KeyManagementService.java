package com.synergism.blog.security.keyManagement.service;

import com.synergism.blog.security.keyManagement.entity.Key;
import com.synergism.blog.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class KeyManagementService {
    /**
     * 从请求头部中获取异世界钥匙
     * 若为空返回空字符串
     * @param request 请求头
     * @return 异世界钥匙
     */
    public String getAnotherWorldKey (HttpServletRequest request){
        String result = request.getHeader(Key.ANOTHER_WORLD_KEY());
        if (StringUtil.checkStringIfEmpty(result))
            return"";
        return result;
    }


    public static String ANOTHER_WORLD_KEY() {
        return Key.ANOTHER_WORLD_KEY();
    }

    public static String EVIL_EYE() {
        return Key.EVIL_EYE();
    }

    public static String ANOTHER_WORLD_RESPONSE() {
        return Key.ANOTHER_WORLD_RESPONSE();
    }

    /**
     * 将全部钥匙名组成字符串
     * @return 字符串
     */
    public static String AllToString() {
        return Key.AllToString();
    }
}
