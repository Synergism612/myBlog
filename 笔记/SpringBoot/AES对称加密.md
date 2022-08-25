# RSA非对称加密

## 创建工具类

创建``src/main/java/com/synergism/blog/util/AESUtil.java``  

## 编写工具类

    package com.synergism.blog.util;

    import org.apache.tomcat.util.codec.binary.Base64;

    import javax.crypto.Cipher;
    import javax.crypto.KeyGenerator;
    import javax.crypto.spec.SecretKeySpec;

    public class AESUtil {

        //参数分别代表 算法名称/加密模式/数据填充方式
        private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

        /**
        * 加密
        * @param src 加密的字符串
        * @param key key值
        * @return
        * @throws Exception
        */
        public static String encrypt(String src, String key) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] b = cipher.doFinal(src.getBytes("utf-8"));
            // 采用base64算法进行转码,避免出现中文乱码
            return Base64.encodeBase64String(b);
        }

        /**
        * 解密
        * @param src 解密的字符串
        * @param key 解密的key值
        * @return
        * @throws Exception
        */
        public static String decrypt(String src, String key) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(src);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        }
    }

## 测试工具类

    @Test
    void AESCodeTest() throws Exception {
      //测试AES加密
      String message = "synergism612731";
      String key = "1234564567890123";
      System.out.println("随机生成的密钥为:" + key);
      String messageEn = AESUtil.encrypt(message,key);
      System.out.println("加密后的字符串为:" + messageEn);
      String messageDe = AESUtil.decrypt(messageEn,key);
      System.out.println("还原后的字符串为:" + messageDe);
    }

效果如下
>随机生成的密钥为:1234564567890123  
>加密后的字符串为:0j09e1PiW6ylZ1YU7tBKWA==  
>还原后的字符串为:synergism612731
