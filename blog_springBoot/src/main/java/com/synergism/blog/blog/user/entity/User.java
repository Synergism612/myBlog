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
 * <p>
 * 
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
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

    public User(Long id, String icon, String name, String username, String password, Date birthday, Integer sex, String intro, Integer status, String power) {
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

    public User(Long id, String icon, String name, String username, String password, Date birthday, Integer sex, String intro, Integer status,
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

    public static User getInstance(Register register){
        return new User(null,"", register.getUsername(), register.getUsername(), register.getPassword(), null,0,null,1,Power.NOT_LOG_IN.getPower());
    }
}
