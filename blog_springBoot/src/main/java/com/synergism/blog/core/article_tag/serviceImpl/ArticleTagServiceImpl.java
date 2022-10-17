package com.synergism.blog.core.article_tag.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.article_tag.entity.ArticleTag;
import com.synergism.blog.core.article_tag.mapper.ArticleTagMapper;
import com.synergism.blog.core.article_tag.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章-标签对照表：文章的标签 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:21
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    private final TagService tagService;

    @Autowired
    public ArticleTagServiceImpl(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public List<List<Tag>> getTagListByArticleIDList(List<Long> articleIDList) {
        //查询对照表
        List<ArticleTag> articleTagList = this.list(new LambdaQueryWrapper<ArticleTag>().in(ArticleTag::getArticleId, articleIDList));
        //查询标签表
        List<Tag> tagList = tagService.listByIds(articleTagList.stream()
                .map(ArticleTag::getTagId)
                .distinct()
                .collect(Collectors.toList()));
        //获得对应标签
        return articleIDList.stream()
                .map(articleID -> {
                    List<Long> tagIDList = articleTagList.stream()
                            .filter(articleTag -> articleTag
                                    .getArticleId()
                                    .equals(articleID))
                            .map(ArticleTag::getTagId).collect(Collectors.toList());

                    return tagIDList.stream()
                            .map(tagID -> {
                                for (Tag tag : tagList) {
                                    if (tag.getId().equals(tagID))
                                        return tag;
                                }
                                return null;
                            }).collect(Collectors.toList());
                }).collect(Collectors.toList());
    }
}
