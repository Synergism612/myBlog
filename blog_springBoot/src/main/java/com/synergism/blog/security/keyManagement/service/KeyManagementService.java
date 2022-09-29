package com.synergism.blog.security.keyManagement.service;

import com.synergism.blog.security.keyManagement.entity.Key;

import javax.servlet.http.HttpServletRequest;

public interface KeyManagementService {
    /**
     * 从请求头部中获取异世界钥匙
     * 若为空返回空字符串
     * @param request 请求头
     * @return 异世界钥匙
     */
    String getAnotherWorldKey (HttpServletRequest request);

    /**
     * 获取异世界钥匙名
     * @return "ANOTHER_WORLD_KEY"
     */
    static String ANOTHER_WORLD_KEY() {
        return Key.ANOTHER_WORLD_KEY();
    }

    /**
     * 获取邪王真眼名
     * @return "EVIL_EYE"
     */
    static String EVIL_EYE() {
        return Key.EVIL_EYE();
    }

    /**
     * 获取异世界回应名
     * @return "ANOTHER_WORLD_RESPONSE"
     */
    static String ANOTHER_WORLD_RESPONSE() {
        return Key.ANOTHER_WORLD_RESPONSE();
    }

    /**
     * 将全部钥匙名组成字符串
     * @return "ANOTHER_WORLD_KEY,EVIL_EYE,ANOTHER_WORLD_RESPONSE"
     */
    static String AllToString() {
        return Key.AllToString();
    }
}
