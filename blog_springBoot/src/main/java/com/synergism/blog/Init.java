package com.synergism.blog;

import com.synergism.blog.util.RSAUtil;

import java.security.NoSuchAlgorithmException;

public class Init {
    /**
     * 用于初始化
     */
    public static void init() throws Exception {
        //初始化非对称密钥对
        RSAUtil.creatKeyPair();
    }
}
