package com.synergism.blog.core.classify.entity;

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
 * 分类表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:03
 */
@Getter
@Setter
@TableName("classify")
@ApiModel(value = "Classify对象", description = "分类表")
public class Classify {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类名")
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


}
