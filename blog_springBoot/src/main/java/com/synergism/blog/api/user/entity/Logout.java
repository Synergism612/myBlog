package com.synergism.blog.api.user.entity;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.utils.StringUtil.checkStringIsEmpty;
import static com.synergism.blog.utils.StringUtil.checkStringIsUnsafe;
import static com.synergism.blog.utils.TypeUtil.asArray;

@Getter
@Setter
public class Logout {
    //登录信息ID
    private String loginID = "";
    //昵称
    private String name = "";
    //账号
    private String username = "";

    Logout(){}

    Logout(String loginID,String name, String username) {
        //判空
        checkStringIsEmpty(asArray(loginID,name,username),asArray("登录信息密钥","账号"));
        //合法性检查
        checkStringIsUnsafe(loginID,name,username);
        this.loginID = loginID;
        this.name = name;
        this.username = username;
    }
}
