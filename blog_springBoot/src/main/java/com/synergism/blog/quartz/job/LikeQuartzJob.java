package com.synergism.blog.quartz.job;

import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.like.service.LikeService;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class LikeQuartzJob extends QuartzJobBean {

    private final LikeService likeService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @Autowired
    public LikeQuartzJob(LikeService likeService, ArticleService articleService, CommentService commentService) {
        this.likeService = likeService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        likeService.getArticleLikeInformation().forEach(articleService::updateLike);
        likeService.getCommentLikeInformation().forEach(commentService::updateLike);
    }
}
