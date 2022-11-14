package com.synergism.blog;

import com.synergism.blog.core.email.entity.CodeMail;
import com.synergism.blog.core.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class BlogApplicationTests {
    Logger logger = LoggerFactory.getLogger(BlogApplicationTests.class);


    private final EmailService emailService;

    @Autowired
    BlogApplicationTests(EmailService emailService) {
        this.emailService = emailService;
    }

    @Test
    public void test() throws MessagingException {
        emailService.sendCodeMail("1273059247@qq.com",new CodeMail("1273059247@qq.com","1234","2022"));
    }
}
