package com.synergism.blog.blog.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.synergism.blog.security.entity.Power;
import com.synergism.blog.utils.TypeUtil;
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
    @TableField("name")
    private String name;

    @ApiModelProperty("账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty("性别(0为女，1为男，3为未知)")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("个人简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("状态码")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("权限")
    @TableField("power")
    private String power;

    /**
     * 构造函数
     * @param id id
     * @param icon 头像
     * @param name 昵称
     * @param username 账号
     * @param password 密码
     * @param birthday 生日
     * @param sex 性别代码
     * @param intro 个人简介
     * @param status 状态码
     * @param power 权限信息(字符串)
     */
    User(Long id, String icon, String name, String username, String password, Date birthday, Integer sex, String intro, Integer status, String power) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
        this.status = status;
        this.power = power;
    }

    /**
     * 构造函数
     * @param id id
     * @param icon 头像
     * @param name 昵称
     * @param username 账号
     * @param password 密码
     * @param birthday 生日
     * @param sex 性别代码
     * @param intro 个人简介
     * @param status 状态码
     * @param power 权限信息(字符串数组)
     */
    User(Long id, String icon, String name, String username, String password, Date birthday, Integer sex, String intro, Integer status,
                String[] power) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.intro = intro;
        this.status = status;
        this.power = TypeUtil.arrayToString(power);
    }

    /**
     * 注册后生成一个基本的用户
     * @param register 注册信息
     * @return 用户
     */
    public static User getInstance(Register register){
        return new User(null,"", register.getUsername(), register.getUsername(), register.getPassword(), null,0,null,1,Power.NOT_LOG_IN.getPower());
    }
}
