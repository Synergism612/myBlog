package com.synergism.blog;

import com.synergism.blog.security.cryptography.service.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化类
 */
@Component
public class Init implements ApplicationRunner {

    private static CryptographyService cryptographyService;

    @Autowired
    Init(CryptographyService cryptographyService) {
        Init.cryptographyService = cryptographyService;
    }

    @Override
    public void run(ApplicationArguments args) {
        String[] keyArray = cryptographyService.initRSAKey();
        System.out.println("公钥---\n" + keyArray[0]);
        System.out.println("私钥---\n" + keyArray[1]);
    }
}
