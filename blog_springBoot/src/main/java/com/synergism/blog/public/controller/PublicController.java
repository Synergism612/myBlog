package com.synergism.blog.global.controller;

import com.synergism.blog.global.entity.Login;
import com.synergism.blog.global.security.enums.RSAEnum;
import com.synergism.blog.global.result.Result;
import org.springframework.web.bind.annotation.*;

import static com.synergism.blog.global.utils.StringUtil.asString;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    /**
     * 请求获取公钥
     * @return 公钥
     */
    @GetMapping("/key")
    public Result<String> getKey() {
        return Result.success(System.getProperty(asString(RSAEnum.PUBLIC_KEY)));
    }

    /**
     * 测试接口
     * @param login 登录信息
     * @return 登录信息
     */
    @PostMapping("/test")
    public Result<String> test(@RequestBody Login login) {
        return Result.success(login.toString());
    }

}
