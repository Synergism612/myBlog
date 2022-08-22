package com.synergism.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-08-22 07:23:12
 */
@Getter
@Setter
@TableName("aboutblog")
@ApiModel(value = "Aboutblog对象", description = "")
public class Aboutblog {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关于博客的公告")
    @TableField("notice")
    private String notice;

    @ApiModelProperty("关于博客的简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("关于博客的内容")
    @TableField("content")
    private String content;


}
