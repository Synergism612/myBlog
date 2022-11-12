package com.synergism.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BlogApplication {

	public static void main(String[] args) {
		//运行
		SpringApplication.run(BlogApplication.class, args);
	}

}
