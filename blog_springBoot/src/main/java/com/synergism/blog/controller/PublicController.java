package com.synergism.blog.controller;

import com.synergism.blog.enums.RSAEnum;
import com.synergism.blog.result.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    /**
     * 请求获取公钥
     * @return 公钥
     */
    @GetMapping("/key")
    public Result<String> getKey() {
        return Result.success(System.getProperty(RSAEnum.PUBLIC_KEY()));
    }

}
