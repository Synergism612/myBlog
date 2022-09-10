package com.synergism.blog.User.controller;

import com.synergism.blog.User.entity.Login;
import com.synergism.blog.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.synergism.blog.security.utils.StringUtil.checkStringIsEmpty;
import static com.synergism.blog.security.utils.StringUtil.checkStringLength;


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
    public void login(Login login) {
        //判空
        checkStringIsEmpty(login.getCounts(), login.getNames());
        //判长
        checkStringLength(login.getUsername(), 11,login.getUsernameName());

    }
}
