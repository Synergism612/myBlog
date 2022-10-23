package com.synergism.blog.api.userAPI.entity;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.core.user.untils.LoginUtil.checkPassword;
import static com.synergism.blog.core.user.untils.LoginUtil.checkUsername;
import static com.synergism.blog.utils.StringUtil.ifEmpty;
import static com.synergism.blog.utils.StringUtil.checkStringIsUnsafe;
import static com.synergism.blog.utils.TypeUtil.asArray;

/**
 * 用户登录实体类
 */
@Getter
@Setter
public class Login {
    //账号
    private String username = "";
    //密码
    private String password = "";

    /**
     * 空参构造函数
     */
    Login(){
    }

    /**
     * 构造函数
     * @param username 账号
     * @param password 密码
     */
    Login(String username, String password) {
        //判空
        ifEmpty(asArray(username,password),asArray("账号","密码"));
        //合法性检查
        checkStringIsUnsafe(username,password);
        //参数检查赋值
        this.username = checkUsername(username);
        this.password = checkPassword(password);
    }
}
