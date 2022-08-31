package com.synergism.blog.controller;

import com.synergism.blog.entity.Login;
import com.synergism.blog.service.UserService;
import com.synergism.blog.util.CheckUtil;
import com.synergism.blog.util.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    UserService service;

    @PostMapping("/login")
    public void login(Login login) {

        System.out.println(login.toString());

        CheckUtil.checkStringIfEmpty(login.getCounts(), login.getNames());
        CheckUtil.checkStringLength(login.getUsername(), 11,login.getUsernameName());

    }
}
