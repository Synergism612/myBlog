package com.synergism.blog.api.email.controller;

import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.api.email.service.EmailService;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    EmailService service;
    UserService userService;

    /**
     * 构造函数
     * 自动注入服务类
     *
     * @param emailService 邮箱服务
     * @param userService  用户服务
     */
    @Autowired
    EmailController(EmailService emailService, UserService userService) {
        this.service = emailService;
        this.userService = userService;
    }

    /**
     * 邮箱验证码接口
     * 返回缓存对应密钥
     *
     * @param mail 邮箱
     * @return 结果[String] 密钥
     */
    @GetMapping("/register/code")
    public Result<String> getRegisterMailCode(@RequestParam String mail, @RequestParam String key) {
        StringUtil.checkStringIsEmpty(mail, "邮箱");
        return service.getRegisterMailCode(mail, key);
    }
}
