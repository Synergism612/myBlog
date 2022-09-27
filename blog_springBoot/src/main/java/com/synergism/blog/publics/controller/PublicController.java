package com.synergism.blog.publics.controller;

import com.synergism.blog.blog.user.entity.Login;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.security.cryptography.enums.RSAEnum;
import com.synergism.blog.result.entity.Result;
import org.springframework.web.bind.annotation.*;

import static com.synergism.blog.utils.StringUtil.asString;

/**
 * 共有接口控制器
 */
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

    /**
     * 错误接口
     * @return 错误
     */
    @GetMapping("/key/error")
    public Result<String> error() {
        return Result.error(CodeMsg.KEY_FAILURE.fillArgs("或许你可以刷新一下？"));
    }
}
