package com.synergism.blog;

import com.synergism.blog.util.AESUtil;
import com.synergism.blog.util.RSAUtil;
import com.synergism.blog.util.Base64Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void base64CodeTest(){
		//测试base64编码
		String src = "synergism612731";
		String encode = Base64Util.encode(src);
		String decode = Base64Util.decode(encode);
		System.out.println(src);
		System.out.println(encode);
		System.out.println(decode);
	}

	@Test
	void RSACodeTest() throws Exception {
		//测试RSA加密
		String message = "synergism612731";
		Map<String, String> keyMap = RSAUtil.getKeyPair();
		System.out.println("随机生成的公钥为:" + keyMap.get("public"));
		System.out.println("随机生成的私钥为:" + keyMap.get("private"));
		String messageEn = RSAUtil.encrypt(message,keyMap.get("public"));
		System.out.println(message + "\t加密后的字符串为:" + messageEn);
		String messageDe = RSAUtil.decrypt(messageEn,keyMap.get("private"));
		System.out.println("还原后的字符串为:" + messageDe);
	}

	@Test
	void AESCodeTest() throws Exception {
		//测试AES加密
		String message = "synergism612731";
		String key = "12345645678901234";
		System.out.println("随机生成的密钥为:" + key);
		String messageEn = AESUtil.encrypt(message,key);
		System.out.println("加密后的字符串为:" + messageEn);
		String messageDe = AESUtil.decrypt(messageEn,key);
		System.out.println("还原后的字符串为:" + messageDe);
	}
}
