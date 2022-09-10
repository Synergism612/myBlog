package com.synergism.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.synergism.blog.Init.init;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) throws Exception {
		//初始化
		init();
		//运行
		SpringApplication.run(BlogApplication.class, args);
	}

}
