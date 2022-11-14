package com.synergism.blog.api.EmailAPI.serviceImpl;

import com.synergism.blog.api.EmailAPI.service.EmailAPIService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.email.service.EmailService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailAPIServiceImpl implements EmailAPIService {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public EmailAPIServiceImpl(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }


    @Override
    public Result<String> getRegisterMailCode(String mail, String key) {
        if (userService.isExist(mail)) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
        } else
            return Result.success(emailService.getMailCode(mail, key));
    }
}
