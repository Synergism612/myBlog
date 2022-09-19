package com.synergism.blog.blog.user.entity;

import com.synergism.blog.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 注册实体类
 */
@Getter
@Setter
public class Register {

    private String username;

    private String password;

    private String code;

    public Register(){
        this.username = "";
        this.password = "";
        this.code = "";
    }

    public Register(String username, String password, String code) {
        //判空
        StringUtil.checkStringIsUnsafe(username,password,code);
        //合法性检查
        StringUtil.checkStringIsUnsafe(username,password);
        this.username = username;
        this.password = password;
        this.code = code;
    }
}
