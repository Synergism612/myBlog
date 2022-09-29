package com.synergism.blog.security.cryptography.service;

public interface CryptographyService {

    /**
     * 初始化函数
     * 用于生成密钥对
     */
    void init();

    /**
     * 密钥验证
     *
     * @param ANOTHER_WORLD_KEY 密钥
     * @return 有效为真，无效为假
     */
    boolean filterVerify(String ANOTHER_WORLD_KEY);

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    String getRSAPublicKey();

    /**
     * 获得私钥
     *
     * @return 私钥
     */
    String getRSAPrivateKey();

    /**
     * 数据库加密
     *
     * @param content 内容
     * @return 密文
     */
    String MySQLEncrypt(String content);

    /**
     * 数据库解密
     *
     * @param content 密文
     * @return 内容
     */
    String MySQLDecrypt(String content);

    /**
     * RSA加密
     *
     * @param content 内容
     * @return 密文
     */
    String RSAEncrypt(String content);

    /**
     * RSA解密
     *
     * @param content 密文
     * @return 内容
     */
    String RSADecrypt(String content);

    /**
     * AES加密
     *
     * @param content 内容
     * @param key     密钥
     * @return 密文
     */
    String AESEncrypt(String content, String key);

    /**
     * AES解密
     *
     * @param content 密文
     * @param key     密钥
     * @return 内容
     */
    String AESDecrypt(String content, String key);

}
