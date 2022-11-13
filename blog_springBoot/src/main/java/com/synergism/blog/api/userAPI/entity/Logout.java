package com.synergism.blog.api.userAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Logout {
    //登录信息ID
    @NotEmpty(message = "登录信息id不能为空")
    private String loginID = "";
    //昵称
    @NotEmpty(message = "昵称不能为空")
    @Size(max = 8,message = "昵称长度不能超过8")
    private String nickname = "";
    //账号
    @NotEmpty(message = "账号不能为空")
    @Email(message = "账号邮箱格式不正确")
    private String username = "";
}
