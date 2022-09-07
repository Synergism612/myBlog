package com.synergism.blog.security;

import com.synergism.blog.security.MyLocker.Utils.RSAUtil;

public class Init {
    /**
     * 用于初始化
     */
    public static void init() throws Exception {
        //初始化非对称密钥对
        RSAUtil.creatKeyPair();
    }
}
