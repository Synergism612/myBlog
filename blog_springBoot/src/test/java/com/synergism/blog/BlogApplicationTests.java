package com.synergism.blog;

import com.synergism.blog.utils.Base64Util;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
	Logger logger = LoggerFactory.getLogger(BlogApplicationTests.class);

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
//		String message = "WjZDeDN2Y05ERTVZcmJUSWtOa3Z1Zz09";
//		Map<String, String> keyMap = RSAUtil.getKeyPair();
//		System.out.println("随机生成的公钥为:" + keyMap.get("public"));
//		System.out.println("随机生成的私钥为:" + keyMap.get("private"));
//		String messageEn = RSAUtil.encrypt(message,keyMap.get("public"));
//		System.out.println(message + "\t加密后的字符串为:" + messageEn);
//		String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ+JQqMb1sVPSPyHs6IdWckn4IJQo/E+S2UerZIrkEPnRPmarodpKhX1+kAuXaPdGek3YFaJLdTBLvwopy0tdhSP1UmavZB3eDBNa+2LiuViZU/+2wgsZczew0PG83FRx8RS5O9nI8hL+D4KLdA/2zEJQDbm6E6e6R1Q5IrFrSvNAgMBAAECgYAJcBBOH+Z7s64Iqmp5C611FfPVIbohGmNRbxgrjoIxUiwla/4pKpJ/zBZq15NYj0rqHgmH9QOOE4OZDTqelJLomnJqWbttScTt/tiVDcMFfFulYNhgV14NKmU53eCYWbbbtNnTRtWaO/gAXnVqPGlqzYv8mQqbxYbh8l32P7QDYQJBAOCS16iyxSHXSV0G3n+lBvza6XrstoRsXFtIUM3A3Dn8t6GHlPK5rfmOO5kEU9Gi9sdUAaOPJzWj4NnA/4fVFRkCQQC13IHqQ+jFZ1bPNZbqFdtEphWpYuqZfN7z+GO/IFfL2ClvbmF5+BBQSRqlE4ShN9E0mTLAh0FrE3vndm/+PU7VAkEAx1NumSx7bmsAkdI8AH3AcwP2wgWCho0LeHX6SnWIwus9LeECYmJOObd1zH4by3Fiqk802TCBc8nOFFiKNcz6WQJAMaNeUnL2tere5u/ZoUsV7kbm4rT9E6fhsvW5RBGv5RO7O48FTCVJTqClhSkuqlJx58lo8OP6Fx/aUfMh3mMdgQI/OudwyILXDWi9HtYft/i1dJmBpUz3NSmpA5cjPMcyJiQt0DQLCncqTVhYMPdeYG+doQQwFxoKfx/PbU5Udke7";
//		String messageDe = RSAUtil.decrypt(message,key);
//		System.out.println("还原后的字符串为:" + messageDe);
	}

	@Test
	void AESCodeTest() throws Exception {
		//测试AES加密
//		String message = "WjZDeDN2Y05ERTVZcmJUSWtOa3Z1Zz09";
//		String key = "12345645678901234";
//		System.out.println("随机生成的密钥为:" + key);
//		String messageEn = AESUtil.encrypt("{ id = \"152\"}","(PITHQNTukorwan_");
//		System.out.println("加密后的字符串为:" + messageEn);
//		String messageDe = AESUtil.decrypt(messageEn,"(PITHQNTukorwan_");
//		System.out.println("还原后的字符串为:" + messageDe);
	}

	@Test
	void logTest() {
		logger.info("你好");
		logger.warn("警告");
		logger.error("错误");
	}
}
