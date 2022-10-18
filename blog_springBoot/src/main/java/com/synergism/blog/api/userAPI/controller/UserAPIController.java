package com.synergism.blog.api.userAPI.controller;

import com.synergism.blog.api.userAPI.entity.Login;
import com.synergism.blog.api.userAPI.entity.Logout;
import com.synergism.blog.api.userAPI.entity.Register;
import com.synergism.blog.api.userAPI.entity.UserInformation;
import com.synergism.blog.email.note.EmailCodeVerifyNote;
import com.synergism.blog.api.userAPI.service.UserAPIService;
import com.synergism.blog.result.Result;
import com.synergism.blog.security.authentication.note.AuthenticationLogoutNote;
import com.synergism.blog.security.cryptography.note.CryptographyPasswordNote;
import com.synergism.blog.security.sessionManagement.note.SessionManagementLoginNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/blog/user")
public class UserAPIController {

    private final UserAPIService service;

    /**
     * 构造函数
     * 自动注入服务类
     *
     * @param service 用户服务类
     */
    @Autowired
    UserAPIController(UserAPIService service) {
        this.service = service;
    }

    /**
     * 登录接口
     *
     * @param login 登录信息
     * @return 结果[用户信息]
     */
    @PostMapping("/login")
    @CryptographyPasswordNote //安全框架密码加密注解
    @SessionManagementLoginNote //安全框架登录绑定会话注解
    public Result<UserInformation> login(@RequestBody Login login) {
        return service.login(login);
    }

    /**
     * 注册接口
     *
     * @param register 注册信息
     * @return 结果[null]
     */
    @PostMapping("/register")
    @CryptographyPasswordNote //安全框架密码加密注解
    @EmailCodeVerifyNote //邮箱验证码校验注解
    public Result<String> register(@RequestBody Register register) {
        return service.register(register);
    }

    /**
     * 登出接口
     *
     * @param logout 登出信息
     * @return 结果[String]
     */
    @PostMapping("/logout")
    @AuthenticationLogoutNote //登出解绑会话注解
    public Result<String> logout(@RequestBody Logout logout) {
        return Result.success("账号" + logout.getName() + "已登出");
    }
}
