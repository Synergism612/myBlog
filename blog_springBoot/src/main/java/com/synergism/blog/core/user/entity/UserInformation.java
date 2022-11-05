package com.synergism.blog.core.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息实体类
 */
@Getter
@Setter
public class UserInformation {
    //ID
    private long id;
    //头像
    private String icon;
    //昵称
    private String nickname;
    //账号
    private String username;
    //生日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    //性别代码
    private Integer sex;
    //性别
    private String sexName;
    //个人简介
    private String intro;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    //至今园龄
    private String upToNow;
    //修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modify_time;

    public UserInformation() {
    }

    /**
     * 构造函数
     *
     * @param icon     头像
     * @param nickname 昵称
     * @param username 账号
     * @param birthday 生日
     * @param sex      性别代码
     * @param intro    个人简介
     */
    public UserInformation(long id, String icon, String nickname, String username, Date birthday, Integer sex, String intro, Date creationTime) {
        this.id = id;
        this.icon = icon;
        this.nickname = nickname;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
        this.creationTime = creationTime;
        this.replenish();
    }

    /**
     * 从用户中获得用户信息
     *
     * @param user 用户
     * @return 用户信息
     */
    public static UserInformation getInstance(User user) {
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

    /**
     * 补充性别与至今时间字段
     */
    public void replenish() {
        switch (this.sex) {
            case 1:
                this.sexName = "男";
                break;
            case 2:
                this.sexName = "女";
                break;
            default:
                this.sexName = "不愿透露";
                break;
        }
        this.upToNow = TimeUtil.upToNow(this.creationTime);
    }

}
