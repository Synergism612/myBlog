package com.synergism.blog.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@ApiModel(value = "User对象", description = "")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("头像")
    @TableField("Icon")
    private String icon;

    @ApiModelProperty("昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty("性别(0为女，1为男，3为未知)")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("个人简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("状态码")
    @TableField("status")
    private Integer status;


}
