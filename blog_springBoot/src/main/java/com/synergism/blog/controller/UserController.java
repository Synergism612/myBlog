package com.synergism.blog.controller;

import com.synergism.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/login")
    public <V> void login(@RequestParam("username") V username, @RequestParam("password") String password){
        Assert.hasText(username.toString(),"用户名不能为空");
        Assert.hasText(password,"密码不能为空");
    }
}
