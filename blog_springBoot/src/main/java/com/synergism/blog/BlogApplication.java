package com.synergism.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

import static com.synergism.blog.Init.init;

@SpringBootApplication
@MapperScan("com/synergism/blog/mapper")
public class BlogApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		//初始化
		init();
		//运行
		SpringApplication.run(BlogApplication.class, args);
	}

}
