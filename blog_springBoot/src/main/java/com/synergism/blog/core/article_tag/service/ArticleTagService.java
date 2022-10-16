package com.synergism.blog.core.article_tag.service;

import com.synergism.blog.core.article_tag.entity.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.tag.entity.Tag;

import java.util.List;

/**
 * <p>
 * 文章-标签对照表：文章的标签 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:21
 */
public interface ArticleTagService extends IService<ArticleTag> {
    /**
     * 通过文章ID列表获取标签列表
     * @param articleIDList 文章ID列表
     * @return 标签列表
     */
    List<List<Tag>> getTagListByArticleIDList(List<Long> articleIDList);
}
