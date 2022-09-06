package com.synergism.blog.controller;

import com.synergism.blog.enums.RSAEnum;
import com.synergism.blog.result.Result;
import org.springframework.web.bind.annotation.*;

import static com.synergism.blog.util.StringUtil.asString;

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
     * @param id id
     * @return id
     */
    @PostMapping("/test")
    public Result<String> test(@RequestBody String id) {
        return Result.success(id);
    }

}
