package com.synergism.blog.api.homepageAPI.entity;

import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserInformationForm {
    //账号
    private String username;
    //昵称
    private String nickname;
    //生日
    private String birthday;
    //性别代码
    private Integer sex;
    //个人简介
    private String intro;
}
