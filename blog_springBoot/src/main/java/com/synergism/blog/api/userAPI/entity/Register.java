package com.synergism.blog.api.userAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 注册实体类
 */
@Getter
@Setter
public class Register {

    //账号
    @NotEmpty(message = "账号不能为空")
    @Email(message = "账号邮箱格式不符合")
    private String username;
    //密码
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "/^[a-zA-Z0-9_-]{8,100}$/", message = "密码应为8到100位的字母、数字、下划线或减号")
    private String password;
    //二次密码输入
    @NotEmpty(message = "再次输入密码不能为空")
    @Pattern(regexp = " /^[a-zA-Z0-9_-]{8,100}$/", message = "密码应为8到100位的字母、数字、下划线或减号")
    private String passwordAgain;
    //验证码
    private String code;
    //缓存验证码key
    private String key;
}
