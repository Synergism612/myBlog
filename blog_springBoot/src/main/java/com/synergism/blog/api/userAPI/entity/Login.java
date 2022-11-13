package com.synergism.blog.api.userAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户登录实体类
 */
@Getter
@Setter
public class Login {
    //账号
    @NotEmpty(message = "账号不能为空")
    @Email(message = "账号邮箱格式不符合")
    private String username;
    //密码
    @NotEmpty(message = "密码不能为空")
    private String password;
}
