package com.synergism.blog.blog.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInformation{
    private String icon;
    private String name;
    private String email;
    private Date birthday;
    private Integer sex;
    private String intro;

    public UserInformation(){

    }

    public UserInformation(String icon, String name, String email, Date birthday, Integer sex, String intro) {
        this.icon = icon;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
    }

    public static UserInformation getInstance(User user){
        return new UserInformation(
                user.getIcon(),
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getSex(),
                user.getIntro()
        );
    }
}
