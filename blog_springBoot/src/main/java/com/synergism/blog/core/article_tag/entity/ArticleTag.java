package com.synergism.blog.core.article_tag.entity;

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
 * 文章-标签对照表：文章的标签
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:21
 */
@Getter
@Setter
@TableName("article_tag")
@ApiModel(value = "ArticleTag对象", description = "文章-标签对照表：文章的标签")
public class ArticleTag {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("article_id")
    private Long articleId;

    @TableField("tag_id")
    private Long tagId;


}
