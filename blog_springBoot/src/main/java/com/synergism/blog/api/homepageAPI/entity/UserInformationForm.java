package com.synergism.blog.api.homepageAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class UserInformationForm {
    //账号
    @NotEmpty(message = "账号不能为空")
    private String username;
    //昵称
    @NotEmpty(message = "昵称不能为空")
    @Size(max = 8,message = "长度不能超过8")
    private String nickname;
    //生日
    @NotEmpty(message = "生日不能为空")
    private String birthday;
    //性别代码
    @NotNull(message = "性别不能为空")
    @Min(value = 0,message = "性别输入错误")
    @Max(value = 2,message = "性别输入错误")
    private Integer sex;
    //个人简介
    @Size(max = 100,message = "长度不能超过100")
    private String intro;
}
