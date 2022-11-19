package com.synergism.blog;

import com.synergism.blog.IO.service.IOService;
import com.synergism.blog.core.email.entity.CodeMail;
import com.synergism.blog.core.email.service.EmailService;
import com.synergism.blog.core.repository.service.FolderService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.File;

@SpringBootTest
class BlogApplicationTests {
    Logger logger = LoggerFactory.getLogger(BlogApplicationTests.class);

    private final String separator = File.separator;

    private final EmailService emailService;
    private final IOService ioService;
    private final FolderService folderService;

    @Autowired
    BlogApplicationTests(EmailService emailService, IOService ioService, FolderService folderService) {
        this.emailService = emailService;
        this.ioService = ioService;
        this.folderService = folderService;
    }

    @Test
    public void test() throws MessagingException {
        emailService.sendCodeMail("1273059247@qq.com",new CodeMail("1273059247@qq.com","1234","2022"));
    }

    @Test
    public void testIO()  {
        String path = "111@qq.com"+separator+"blog"+separator+"icon";
        folderService.update(1l,path);
        ioService.mkdir(path);
    }
}
