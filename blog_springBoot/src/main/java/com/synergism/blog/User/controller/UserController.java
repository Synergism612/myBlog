package com.synergism.blog.user.controller;

import com.synergism.blog.result.entity.Result;
import com.synergism.blog.user.entity.Login;
import com.synergism.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.synergism.blog.utils.StringUtil.checkStringIsEmpty;
import static com.synergism.blog.utils.StringUtil.checkStringLength;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    UserController(UserService service){
        this.service =service;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody Login login) {
        return Result.success(login.toString());
    }
}
