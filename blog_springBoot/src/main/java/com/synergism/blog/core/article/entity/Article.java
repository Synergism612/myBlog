package com.synergism.blog.core.article.entity;

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
 * 文章表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
@Getter
@Setter
@TableName("article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("头像")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("正文")
    @TableField("body")
    private String body;

    @ApiModelProperty("摘要")
    @TableField("synopsis")
    private String synopsis;

    @ApiModelProperty("浏览量")
    @TableField("views")
    private Long views;

    @ApiModelProperty("点赞数")
    @TableField("like_count")
    private Long likeCount;

    @ApiModelProperty("是否私有")
    @TableField("if_private")
    private int ifPrivate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    private Date creationTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
