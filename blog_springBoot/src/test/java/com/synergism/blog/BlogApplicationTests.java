package com.synergism.blog;

import com.synergism.blog.entity.Aboutblog;
import com.synergism.blog.mapper.AboutblogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private AboutblogMapper aboutblogMapper;

	@Test
	void testInsert() {
	//测试输入
		Aboutblog sample = new Aboutblog();
		sample.setId(1L);
		sample.setContent("123");
		sample.setIntro("123");
		sample.setNotice("123");
		aboutblogMapper.insert(sample);
		assert (!sample.getId().toString().isEmpty()):"出现空值";
	}
}
