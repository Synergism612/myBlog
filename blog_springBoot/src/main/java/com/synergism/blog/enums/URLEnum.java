package com.synergism.blog.enums;

import lombok.Getter;

@Getter
public enum URLEnum {
    POST,GET,PUT,DELETE;

    /**
     * 用于存放公钥获取路劲
     */
    private static String PublicKeyPath = "/api/public/key";

    public static String PublicKeyPath() {
        return PublicKeyPath;
    }
}
