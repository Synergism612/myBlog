package com.synergism.blog;

import com.synergism.blog.util.RSAUtil;
import com.synergism.blog.util.base64Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void base64CodeTest(){
		//测试base64编码
		String src = "synergism612731";
		String encode = base64Util.encode(src);
		String decode = base64Util.decode(encode);
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
}
