package com.synergism.blog.core.tag.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:34
 */
@Getter
@Setter
@TableName("tag")
@ApiModel(value = "Tag对象", description = "标签表")
public class Tag {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标签名")
    @TableField("name")
    private String name;

    @ApiModelProperty("注释")
    @TableField("annotation")
    private String annotation;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Tag(){}

    public Tag(String name, String annotation) {
        this.name = name;
        this.annotation = annotation;
    }
}
