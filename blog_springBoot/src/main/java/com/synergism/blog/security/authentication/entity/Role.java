package com.synergism.blog.security.authentication.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色代码")
    @TableField("code")
    private Integer code;

    @ApiModelProperty("注释")
    @TableField("annotation")
    private String annotation;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    private String creationTime;

    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private String modifyTime;

}
