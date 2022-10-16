package com.synergism.blog.core.article_comment.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.synergism.blog.core.article_comment.mapper.ArticleCommentMapper;
import com.synergism.blog.core.article_comment.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private final CommentService commentService;

    @Autowired
    public ArticleCommentServiceImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public List<List<CommentInformation>> getCommentInformationListByArticleIDList(List<Long> articleIDList) {
        //查询对照表
        List<ArticleComment> articleCommentList = this.list(new LambdaQueryWrapper<ArticleComment>().in(ArticleComment::getArticleId, articleIDList));

        List<CommentInformation> commentInformationList = commentService.getAllComment();

        //获取对应分类
        return articleIDList.stream()
                .map(articleID -> {
                    List<Long> commentIDList = articleCommentList.stream().map(articleComment -> {
                        if (articleComment.getArticleId().equals(articleID))
                            return articleComment.getCommentId();
                        return null;
                    }).collect(Collectors.toList());

                    return commentIDList.stream().map(commentID -> {
                        for (CommentInformation commentInformation : commentInformationList) {
                            if (commentInformation.getId().equals(commentID))
                                return commentInformation;
                        }
                        return null;
                    }).collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }
}
