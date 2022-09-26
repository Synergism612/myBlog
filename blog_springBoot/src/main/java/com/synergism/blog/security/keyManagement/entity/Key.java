package com.synergism.blog.security.keyManagement.entity;

/**
 * 密钥管理实体类
 */
public class Key {
    private static final String ANOTHER_WORLD_KEY = "ANOTHER_WORLD_KEY";
    private static final String AUTH_ID = "AUTH_ID";
    private static final String ANOTHER_WORLD_RESPONSE = "ANOTHER_WORLD_RESPONSE";


    public static String ANOTHER_WORLD_KEY() {
        return ANOTHER_WORLD_KEY;
    }

    public static String AUTH_ID() {
        return AUTH_ID;
    }

    public static String ANOTHER_WORLD_RESPONSE() {
        return ANOTHER_WORLD_RESPONSE;
    }

    /**
     * 将全部钥匙名组成字符串
     * @return 字符串
     */
    public static String AllToString() {
        return ANOTHER_WORLD_KEY + "," +
                AUTH_ID + "," +
                ANOTHER_WORLD_RESPONSE;
    }
}
