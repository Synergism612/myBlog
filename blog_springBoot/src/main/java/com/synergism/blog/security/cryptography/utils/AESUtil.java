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
    //示例密钥 密钥须有16位
//    private static final String KEY = "1234567890hijklm";

    //偏移量，AES 128位数据块对应偏移量为16位 下面我使用原密码作为偏移量，所以没有用到
//    public static final String VIPARA = "1234567890abcdef";   //AES 128位数据块对应偏移量为16位

    /*
     * 用于新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
     * 1. 加密的类型(如AES，DES，RC2等)
     * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
     * 3. 补码方式(包含nopadding/PKCS5Padding等等)
     * 依据这三个参数可以创建很多种加密方式
     */

    //AES：加密方式   CBC：工作模式   PKCS5Padding：填充模式
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    //AES后续会用到
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

        //特例
        if (content == null || "".equals(content)) {
            return content;
        }

        try {
            //新建一个密码编译器的实例
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);

            //偏移量 使用密钥作为偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(CODE_TYPE));

            //转为字节数组
            byte[] byteContent = content.getBytes(CODE_TYPE);

            //载入秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CODE_TYPE), AES);

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv);

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64.encodeBase64String(result);
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
        //特例
        if (content == null || "".equals(content)) {
            return content;
        }
        try {
            //新建实例
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);

            //使用密钥作为偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(CODE_TYPE));

            //载入秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CODE_TYPE), AES);

            //解密初始化
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, zeroIv);

            //解密
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            //转为字符串
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
            //创建AES的密码生成实例
            kg = KeyGenerator.getInstance(AES);

            //AES 要求密钥长度为 128 也就是16位字符
            kg.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), AES);
        } catch (Exception e) {
            throw new KeyFailureException("错误的密钥");
        }
    }

}
