package com.synergism.blog.core.article_comment.serviceImpl;

import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.synergism.blog.core.article_comment.mapper.ArticleCommentMapper;
import com.synergism.blog.core.article_comment.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章-评论对照表：文章的评论 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:47
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

}
