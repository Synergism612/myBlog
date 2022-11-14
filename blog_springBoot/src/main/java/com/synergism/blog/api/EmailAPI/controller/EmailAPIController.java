package com.synergism.blog.api.EmailAPI.controller;

import com.synergism.blog.api.EmailAPI.service.EmailAPIService;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/mail")
public class EmailAPIController {

    EmailAPIService service;

    @Autowired
    EmailAPIController(EmailAPIService emailAPIService) {
        this.service = emailAPIService;
    }

    /**
     * 邮箱验证码接口
     * 返回缓存对应密钥
     *
     * @param mail 邮箱
     * @return 结果[String] 密钥
     */
    @Validated
    @GetMapping("/register/code")
    public Result<String> getRegisterMailCode(
            @RequestParam @NotEmpty(message = "账号邮箱不能为空") String mail,
            @RequestParam String key
    ) {
        return service.getRegisterMailCode(mail, key);
    }
}
