package com.synergism.blog.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.security.entity.Auth;
import com.synergism.blog.security.enums.KeyEnum;
import com.synergism.blog.security.enums.RSAEnum;
import com.synergism.blog.security.utils.RSAUtil;
import com.synergism.blog.user.entity.Login;
import com.synergism.blog.user.entity.User;
import com.synergism.blog.user.entity.UserInformation;
import com.synergism.blog.user.service.UserService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.utils.StringUtil.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@RestController
@RequestMapping("blog/user")
public class UserController {

    private final UserService service;
    private final RedisService redis;

    private final String private_key = System.getProperty(asString(RSAEnum.PRIVATE_KEY));

    @Autowired
    UserController(UserService service, RedisService redis) {
        this.service = service;
        this.redis = redis;
    }

    @PostMapping("/login")
    public Result<UserInformation> login(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        //获得对应用户
        User user = service.getOne(new QueryWrapper<User>().eq("email", login.getUsername()));
        //对象判空
        TypeUtil.isNull(user);
        //密码比对
        if (RSAUtil.decryptDataOnJava(user.getPassword(), private_key)
                .equals(login.getPassword())) {
            //更新redis中的记录
            String AUTH_ID = request.getHeader(asString(KeyEnum.AUTH_ID));
            Auth auth = (Auth) redis.getValue(AUTH_ID);
            auth.updateFromUser(user);
            redis.getAndSetValue(AUTH_ID, auth);

            //更新响应头中的id
            response.setHeader(asString(KeyEnum.AUTH_ID), AUTH_ID);

            //返回成功
            return Result.success(UserInformation.getInstance(user));
        }
        //返回失败
        return Result.error(CodeMsg.USERNAME_ERROR);
    }
}
