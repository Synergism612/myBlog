package com.synergism.blog.core.comment_article.serviceImpl;

import com.synergism.blog.core.comment_article.entity.CommentArticle;
import com.synergism.blog.core.comment_article.mapper.CommentArticleMapper;
import com.synergism.blog.core.comment_article.service.CommentArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章-评论对照表：文章的评论 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:54:07
 */
@Service
public class CommentArticleServiceImpl extends ServiceImpl<CommentArticleMapper, CommentArticle> implements CommentArticleService {

}
