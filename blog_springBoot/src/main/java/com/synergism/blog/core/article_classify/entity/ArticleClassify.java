package com.synergism.blog.core.article_classify.entity;

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
 * 分类-文章对照表：文章的分类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:57:44
 */
@Getter
@Setter
@TableName("article_classify")
@ApiModel(value = "ArticleClassify对象", description = "分类-文章对照表：文章的分类")
public class ArticleClassify {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("classify_id")
    private Long classifyId;

    @TableField("article_id")
    private Long articleId;


}
