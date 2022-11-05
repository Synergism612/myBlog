package com.synergism.blog.core.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.api.userAPI.entity.Register;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据库用户实体类
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "User对象", description = "用户表")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("头像")
    @TableField("Icon")
    private String icon;

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("生日")
    @TableField("birthday")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @ApiModelProperty("性别(0为未知，1为男，2为女)")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("个人简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 构造函数
     *
     * @param id           id
     * @param icon         头像
     * @param nickname         昵称
     * @param username     账号
     * @param password     密码
     * @param birthday     生日
     * @param sex          性别代码
     * @param intro        个人简介
     * @param creationTime 创建时间
     * @param modifyTime   修改时间
     */
    public User(Long id, String icon, String nickname, String username, String password, Date birthday, Integer sex, String intro, Date creationTime, Date modifyTime) {
        this.id = id;
        this.icon = icon;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
        this.creationTime = creationTime;
        this.modifyTime = modifyTime;
    }

    /**
     * 注册后生成一个基本的用户
     *
     * @param register 注册信息
     * @return 用户
     */
    public static User getInstance(Register register) {
        Date now = new Date();
        return new User(null, "", register.getUsername(), register.getUsername(), register.getPassword(), null, 0, null, now, now);
    }
}
