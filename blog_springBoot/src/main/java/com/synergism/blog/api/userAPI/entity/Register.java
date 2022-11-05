package com.synergism.blog.api.userAPI.entity;

import com.synergism.blog.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.utils.TypeUtil.asArray;

/**
 * 注册实体类
 */
@Getter
@Setter
public class Register {

    //账号
    private String username;
    //密码
    private String password;
    //二次密码输入
    private String passwordAgen;
    //验证码
    private String code;

    //缓存验证码key
    private String key;

    /**
     * 空参构造函数
     */
    Register(){
        this.username = "";
        this.password = "";
        this.code = "";
        this.key = "";
    }

    /**
     * 构造函数
     * @param username 账号
     * @param password 密码
     * @param code 验证码
     * @param key 验证码密钥
     */
    Register(String username, String password,String passwordAgen, String code,String key) {
        //判空
        StringUtil.ifEmpty(asArray(username,password,passwordAgen,code),asArray("账号","密码","二次输入密码","验证码"));
        //合法性检查
        StringUtil.checkStringIsUnsafe(username,password,passwordAgen);
        this.username = username;
        this.password = password;
        this.code = code;
        this.key = key;
    }
}
