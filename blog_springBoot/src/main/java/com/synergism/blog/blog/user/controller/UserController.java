package com.synergism.blog.blog.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.blog.user.entity.Register;
import com.synergism.blog.email.note.EmailCodeVerifyNote;
import com.synergism.blog.exception.custom.RegisterFailException;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.blog.user.entity.Login;
import com.synergism.blog.blog.user.entity.User;
import com.synergism.blog.blog.user.entity.UserInformation;
import com.synergism.blog.blog.user.service.UserService;
import com.synergism.blog.security.cryptography.note.CryptographyPasswordNote;
import com.synergism.blog.security.sessionManagement.note.SessionManagementLoginNote;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@RestController
@RequestMapping("api/blog/user")
public class UserController {

    private final UserService service;

    /**
     * 构造函数
     * 自动注入服务类
     * @param service 用户服务类
     */
    @Autowired
    UserController(UserService service) {
        this.service = service;
    }

    /**
     * 登录接口
     * @param login 登录信息
     * @return 结果[用户信息]
     */
    @PostMapping("/login")
    @CryptographyPasswordNote //安全框架密码加密注解
    @SessionManagementLoginNote //安全框架登录绑定会话注解
    public Result<UserInformation> login(@RequestBody Login login) {
        //获得对应用户
        User user = service.getOne(new QueryWrapper<User>().eq("username", login.getUsername()));
        //对象判空
        TypeUtil.isNull(user);
        //密码比对
        if (user.getPassword().equals(login.getPassword())){
            //返回成功
            return Result.success(UserInformation.getInstance(user));
        }
        //返回失败
        return Result.error(CodeMsg.USERNAME_ERROR);
    }

    /**
     * 注册接口
     * @param register 注册信息
     * @return 结果[null]
     */
    @PostMapping("/register")
    @CryptographyPasswordNote //安全框架密码加密注解
    @EmailCodeVerifyNote //邮箱验证码校验注解
    public Result<String> register(@RequestBody Register register) {
        //账号是否已存在判断
        if (service.ifExist(register.getUsername())) {
            throw new RegisterFailException("账号已存在");
        }
        //保存到数据库
        service.save(User.getInstance(register));
        //返回成功
        return Result.success();
    }
}
