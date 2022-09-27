package com.synergism.blog.security.keyManagement.entity;

/**
 * 密钥管理实体类
 */
public class Key {
    private static final String ANOTHER_WORLD_KEY = "ANOTHER_WORLD_KEY";
    private static final String EVIL_EYE = "EVIL_EYE";
    private static final String ANOTHER_WORLD_RESPONSE = "ANOTHER_WORLD_RESPONSE";


    public static String ANOTHER_WORLD_KEY() {
        return ANOTHER_WORLD_KEY;
    }

    public static String EVIL_EYE() {
        return EVIL_EYE;
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
                EVIL_EYE + "," +
                ANOTHER_WORLD_RESPONSE;
    }
}
