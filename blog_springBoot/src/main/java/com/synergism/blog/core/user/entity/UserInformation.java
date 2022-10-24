package com.synergism.blog.core.user.entity;

import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息实体类
 */
@Getter
@Setter
public class UserInformation{
    //ID
    private long id;
    //头像
    private String icon;
    //昵称
    private String nickname;
    //账号
    private String username;
    //生日
    private Date birthday;
    //性别代码
    private String sex;
    //个人简介
    private String intro;
    //至今园龄
    private String upToNow;

    /**
     * 构造函数
     * @param icon 头像
     * @param nickname 昵称
     * @param username 账号
     * @param birthday 生日
     * @param sex 性别代码
     * @param intro 个人简介
     */
    public UserInformation(long id,String icon, String nickname, String username, Date birthday, Integer sex, String intro,Date creationTime) {
        this.id = id;
        this.icon = icon;
        this.nickname = nickname;
        this.username = username;
        this.birthday = birthday;
        switch (sex){
            case 0:this.sex = "不愿透露";
            break;
            case 1:this.sex = "男";
                break;
            case 2:this.sex = "女";
                break;
        }
        this.intro = intro;
        this.upToNow = TimeUtil.upToNow(creationTime);
    }

    public UserInformation(UserInformation userInformation) {
        this.id = userInformation.getId();
        this.icon = userInformation.getIcon();
        this.nickname = userInformation.getNickname();
        this.username = userInformation.getUsername();
        this.birthday = userInformation.getBirthday();
        this.sex = userInformation.getSex();
        this.intro = userInformation.getIntro();
        this.upToNow = userInformation.getUpToNow();
    }

    /**
     * 从用户中获得用户信息
     * @param user 用户
     * @return 用户信息
     */
    public static UserInformation getInstance(User user){
        return new UserInformation(
                user.getId(),
                user.getIcon(),
                user.getNickname(),
                user.getUsername(),
                user.getBirthday(),
                user.getSex(),
                user.getIntro(),
                user.getCreationTime()
        );
    }
}
