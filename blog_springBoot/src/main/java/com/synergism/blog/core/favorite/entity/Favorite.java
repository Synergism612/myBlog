package com.synergism.blog.core.favorite.entity;

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
 * 收藏夹表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:32:37
 */
@Getter
@Setter
@TableName("favorite")
@ApiModel(value = "Favorite对象", description = "收藏夹表")
public class Favorite {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分组名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("注释")
    @TableField("annotation")
    private String annotation;

    @ApiModelProperty("私有  0-公开 1-私有")
    @TableField("if_private")
    private Integer ifPrivate;
}
