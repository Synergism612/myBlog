package com.synergism.blog.security.utils;

import com.synergism.blog.exception.custom.KeyFailureException;
import com.synergism.blog.security.enums.RSAEnum;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import static com.synergism.blog.utils.StringUtil.asString;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 */
public class RSAUtil {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = asString(RSAEnum.PUBLIC_KEY);

    
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = asString(RSAEnum.PRIVATE_KEY);

    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    
    /**
     * RSA 位数 如果采用2048 上面最大加密和最大解密则须填写:  245 256
     */
    private static final int INITIALIZE_LENGTH = 1024;

    
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return 密钥对
     
     */
    public static Map<String, Object> genKeyPair(){
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(INITIALIZE_LENGTH);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = new HashMap(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        } catch (Exception e) {
            throw new KeyFailureException("错误的生成");
        }
    }

    
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     *
     * @return 签名
     
     */
    public static String sign(byte[] data, String privateKey){
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(data);
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            throw new KeyFailureException("错误的签名");
        }
    }

    
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @param sign
     *            数字签名
     *
     * @return 签名是否通过校验
     
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign){
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicK = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicK);
            signature.update(data);
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            throw new KeyFailureException("错误的校验");
        }
    }

    
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return 解密内容
     
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey){
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            return cipherInitGetData(keyFactory,privateK,encryptedData);
        } catch (Exception e) {
            throw new KeyFailureException("错误的解密");
        }
    }

    
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData
     *            内容
     * @param publicKey
     *            公钥(BASE64编码)
     * @return 密文
     
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey){
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            return cipherInitGetData(keyFactory,publicK,encryptedData);
        } catch (Exception e) {
            throw new KeyFailureException("错误的解密");
        }
    }

    
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return 密文
     
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey){
        try {
            byte[] keyBytes = Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            return cipherInitGetData(keyFactory,publicK,data);
        } catch (Exception e) {
            throw new KeyFailureException("错误的加密");
        }
    }

    
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return 密文
     
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey){
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            return cipherInitGetData(keyFactory,privateK,data);
        } catch (Exception e) {
            throw new KeyFailureException("错误的加密");
        }
    }

    
    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return 私钥
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    
    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return 公钥
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * java端公钥加密
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) {
        try {
            data = Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        } catch (Exception e) {
            throw new KeyFailureException("错误的加密");
        }
        return data;
    }

    /**
     * java端私钥解密
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) {
        String temp;
        try {
            byte[] rs = Base64.decodeBase64(data);
            temp = new String(RSAUtil.decryptByPrivateKey(rs, PRIVATEKEY), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new KeyFailureException("错误的解密");
        }
        return temp;
    }

    /**
     //     * 用于生成密钥对并存储到系统属性中
     //     */
    public static void creatKeyPair(){
        try {
            Map<String, Object> genKeyPair = genKeyPair();
            System.out.println("公钥:" + getPublicKey(genKeyPair));
            System.out.println("私钥:" + getPrivateKey(genKeyPair));

            System.setProperty(PUBLIC_KEY, getPublicKey(genKeyPair));
            System.setProperty(PRIVATE_KEY, getPrivateKey(genKeyPair));
        } catch (Exception e) {
            throw new KeyFailureException("错误的密钥");
        }
    }

    private static byte[] cipherInitGetData(KeyFactory keyFactory, Key privateK, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
}

