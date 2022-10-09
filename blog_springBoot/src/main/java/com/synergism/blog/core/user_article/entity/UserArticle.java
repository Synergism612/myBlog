package com.synergism.blog.core.user_article.entity;

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
 * 用户-文章对照表：用户的文章
 * </p>
 *
 * @author Synergism
 * @since 2022-10-09 04:43:45
 */
@Getter
@Setter
@TableName("user_article")
@ApiModel(value = "UserArticle对象", description = "用户-文章对照表：用户的文章")
public class UserArticle {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户主键")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("文章主键")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("私有  0-公开 1-私有")
    @TableField("if_private")
    private Integer ifPrivate;


}
