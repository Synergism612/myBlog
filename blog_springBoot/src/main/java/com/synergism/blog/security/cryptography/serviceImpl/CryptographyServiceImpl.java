package com.synergism.blog.security.cryptography.serviceImpl;

import com.synergism.blog.exception.custom.KeyFailureException;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.cryptography.utils.AESUtil;
import com.synergism.blog.security.cryptography.utils.RSAUtil;
import com.synergism.blog.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CryptographyServiceImpl implements CryptographyService {

    private String RSAPublicKey;
    private String RSAPrivateKey;

    private final String MySQLKey = "i6Dm1E76doFdR+nX";

    public void init() {
        //创造新密钥对
        Map<String, Object> keyPair = RSAUtil.genKeyPair();
        //获取公钥私钥
        this.RSAPublicKey = RSAUtil.getPrivateKey(keyPair);
        System.out.println("公钥---\n" +  this.RSAPublicKey);
        this.RSAPrivateKey = RSAUtil.getPrivateKey(keyPair);
        System.out.println("私钥---\n" + this.RSAPrivateKey);
    }

    /**
     * 密钥验证
     * @param ANOTHER_WORLD_KEY 密钥
     * @return 有效为真，无效为假
     */
    public boolean filterVerify(String ANOTHER_WORLD_KEY){
        try {
            StringUtil.checkStringIsEmpty(ANOTHER_WORLD_KEY,"异世界钥匙");
            this.RSADecrypt(ANOTHER_WORLD_KEY);
            return true;
        }catch (KeyFailureException e){
            return false;
        }
    }

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    public String getRSAPublicKey() {
        return RSAPublicKey;
    }

    /**
     * 获得私钥
     *
     * @return 私钥
     */
    public String getRSAPrivateKey() {
        return RSAPrivateKey;
    }

    /**
     * 数据库加密
     *
     * @param content 内容
     * @return 密文
     */
    public String MySQLEncrypt(String content) {
        return this.AESEncrypt(content, this.MySQLKey);
    }

    /**
     * 数据库解密
     *
     * @param content 密文
     * @return 内容
     */
    public String MySQLDecrypt(String content) {
        return this.AESDecrypt(content, this.MySQLKey);
    }

    /**
     * RSA加密
     *
     * @param content 内容
     * @return 密文
     */
    public String RSAEncrypt(String content) {
        return RSAUtil.encryptedDataOnJava(content, this.getRSAPublicKey());
    }

    /**
     * RSA解密
     *
     * @param content 密文
     * @return 内容
     */
    public String RSADecrypt(String content) {
        return RSAUtil.decryptDataOnJava(content, this.getRSAPrivateKey());
    }

    /**
     * AES加密
     *
     * @param content 内容
     * @param key     密钥
     * @return 密文
     */
    public String AESEncrypt(String content, String key) {
        return AESUtil.encrypt(content, key);
    }

    /**
     * AES解密
     *
     * @param content 密文
     * @param key     密钥
     * @return 内容
     */
    public String AESDecrypt(String content, String key) {
        return AESUtil.decrypt(content, key);
    }

}
