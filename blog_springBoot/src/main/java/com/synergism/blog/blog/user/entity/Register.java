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

    //账号
    private String username;
    //密码
    private String password;
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
     */
    Register(String username, String password, String code,String key) {
        //判空
        StringUtil.checkStringIsUnsafe(username,password,code,key);
        //合法性检查
        StringUtil.checkStringIsUnsafe(username,password);
        this.username = username;
        this.password = password;
        this.code = code;
        this.key = key;
    }
}
