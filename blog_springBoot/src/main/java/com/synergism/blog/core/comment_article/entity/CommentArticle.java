package com.synergism.blog.core.comment_article.entity;

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
 * 文章-评论对照表：文章的评论
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:54:07
 */
@Getter
@Setter
@TableName("comment_article")
@ApiModel(value = "CommentArticle对象", description = "文章-评论对照表：文章的评论")
public class CommentArticle {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文章主键")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("评论主键")
    @TableField("comment_id")
    private Long commentId;


}
