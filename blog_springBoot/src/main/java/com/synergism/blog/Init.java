package com.synergism.blog;

import com.synergism.blog.security.authentication.service.AuthenticationService;
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

    private static AuthenticationService authenticationService;

    @Autowired
    Init(CryptographyService cryptographyService, AuthenticationService authenticationService) {
        Init.cryptographyService = cryptographyService;
        Init.authenticationService = authenticationService;
    }

    @Override
    public void run(ApplicationArguments args) {
        cryptographyService.init();
        authenticationService.init();
    }

//    @Scheduled(cron = "0 0 * * */7")
//    public void update (){
//
//    }
}
