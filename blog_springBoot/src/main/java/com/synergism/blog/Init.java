package com.synergism.blog.global;

import com.synergism.blog.global.security.utils.RSAUtil;

public class Init {
    /**
     * 用于初始化
     */
    public static void init() throws Exception {
        //初始化非对称密钥对
        RSAUtil.creatKeyPair();
    }
}
