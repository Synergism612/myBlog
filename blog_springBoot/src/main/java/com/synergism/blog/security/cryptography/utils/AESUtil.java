package com.synergism.blog.security.cryptography.utils;

import com.synergism.blog.exception.custom.KeyFailureException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES对称加密工具类
 */
public class AESUtil {
//    private static final String KEY = "1234567890hijklm";

    //偏移量，AES 128位数据块对应偏移量为16位
//    public static final String VIPARA = "1234567890abcdef";   //AES 128位数据块对应偏移量为16位

    //AES：加密方式   CBC：工作模式   PKCS5Padding：填充模式
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
//    private static final String CBC_PKCS5_PADDING = "CBC";


    private static final String AES = "AES";

    //编码方式
    public static final String CODE_TYPE = "UTF-8";


    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {

        if (content == null || "".equals(content)) {
            return content;
        }

        try {
            /*
             * 新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
             * 1. 加密的类型(如AES，DES，RC2等)
             * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
             * 3. 补码方式(包含nopadding/PKCS5Padding等等)
             * 依据这三个参数可以创建很多种加密方式
             */
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);

            //偏移量 使用密钥作为偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(CODE_TYPE));

            byte[] byteContent = content.getBytes(CODE_TYPE);

            //使用加密秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CODE_TYPE), AES);
            //SecretKeySpec skeySpec = getSecretKey(key);

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception e) {
            throw new KeyFailureException("错误的加密");
        }
    }


    /**
     * AES 解密操作
     *
     * @param content 密文
     * @param key     密钥
     * @return 解密后的内容
     */
    public static String decrypt(String content, String key) {
        if (content == null || "".equals(content)) {
            return content;
        }

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            //使用密钥作为偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(CODE_TYPE));

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CODE_TYPE), AES);
            //SecretKeySpec skeySpec = getSecretKey(key);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, zeroIv);

            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, CODE_TYPE);
        } catch (Exception e) {
            throw new KeyFailureException("错误的解密");
        }
    }


    /**
     * 生成密钥
     *
     * @param key 生成密钥的值
     * @return 密钥
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg;

        try {
            kg = KeyGenerator.getInstance(AES);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), AES);// 转换为AES专用密钥
        } catch (Exception e) {
            throw new KeyFailureException("错误的密钥");

        }
    }

}
