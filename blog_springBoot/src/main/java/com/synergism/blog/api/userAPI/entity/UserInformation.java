package com.synergism.blog.api.userAPI.entity;

import com.synergism.blog.core.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息实体类
 */
@Getter
@Setter
public class UserInformation{
    //头像
    private String icon = "";
    //昵称
    private String name = "";
    //账号
    private String username = "";
    //生日
    private Date birthday = new Date();
    //性别代码
    private Integer sex = 0;
    //个人简介
    private String intro = "";

    /**
     * 空参构造函数
     */
    public UserInformation(){
    }

    /**
     * 构造函数
     * @param icon 头像
     * @param name 昵称
     * @param username 账号
     * @param birthday 生日
     * @param sex 性别代码
     * @param intro 个人简介
     */
    public UserInformation(String icon, String name, String username, Date birthday, Integer sex, String intro) {
        this.icon = icon;
        this.name = name;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
    }

    /**
     * 从用户中获得用户信息
     * @param user 用户
     * @return 用户信息
     */
    public static UserInformation getInstance(User user){
        return new UserInformation(
                user.getIcon(),
                user.getName(),
                user.getUsername(),
                user.getBirthday(),
                user.getSex(),
                user.getIntro()
        );
    }
}
