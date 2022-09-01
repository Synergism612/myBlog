package com.synergism.blog.util;

import com.synergism.blog.enums.RSAEnum;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {
    /**
     * 随机生成密钥对
     * @return 返回一个包含密码对的表
     */
    public static Map<String, String> getKeyPair() throws NoSuchAlgorithmException {
        Map<String, String> result = new HashMap<>();
        //KeyPairGenerator对象用于生成密钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化生成器，使用给定的随机源（和默认的参数集合）初始化确定密钥大小的密钥对生成器
        keyPairGenerator.initialize(1024, SecureRandom.getInstanceStrong());
        //生成密钥对存放在KeyPair中
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        //得到公钥字符串
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        //得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

        result.put(RSAEnum.PUBLIC(), publicKeyString);  //存放公钥
        result.put(RSAEnum.PRIVATE(), privateKeyString);  //存放私钥

        return result; //返回密钥对
    }
    /**
     * RSA公钥加密
     *
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * 用于生成密钥对并存储到系统属性中
     */
    public static void creatKeyPair() throws NoSuchAlgorithmException {
        Map<String,String> KeyPair = getKeyPair();
        System.setProperty(RSAEnum.PUBLIC(), KeyPair.get(RSAEnum.PUBLIC()));
        System.setProperty(RSAEnum.PRIVATE(), KeyPair.get(RSAEnum.PRIVATE()));
    }

}
