package com.synergism.blog.core.article.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.api.userAPI.entity.UserInformation;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user_article.entity.SignedArticle;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ArticleInformation {
    //文章主键
    private long id;
    //文章标题
    private String title;
    //文章头像
    private String icon;
    //文章内容
    private String body;
    //文章摘要
    private String synopsis;
    //文章浏览量
    private String views;
    //文章点赞量
    private String likeCount;
    //文章评论数
    private int commentCount;
    //文章评论列表
    private List<CommentInformation> commentInformationList;
    //作者信息
    private UserInformation userInformation;
    //是否私有
    private int ifPrivate;
    //分类
    private List<Classify> classifyList;
    //标签
    private List<Tag> tagList;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    //修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public ArticleInformation(SignedArticle signedArticle,int commentCount,List<CommentInformation> commentInformationList,List<Classify> classifyList,List<Tag> tagList){
        Article article = signedArticle.getArticle();
        this.id = article.getId();
        this.icon = article.getIcon();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.synopsis = article.getSynopsis()+"...";
        this.views = article.getViews();
        this.likeCount = article.getLikeCount();
        this.creationTime = article.getCreationTime();
        this.modifyTime = article.getModifyTime();
        this.userInformation = signedArticle.getUserInformation();
        this.ifPrivate = signedArticle.getIfPrivate();
        this.commentCount = commentCount;
        this.commentInformationList = commentInformationList;
        this.classifyList = classifyList;
        this.tagList = tagList;
    }

    public ArticleInformation(Article article){
        this.id = article.getId();
        this.icon = article.getIcon();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.synopsis = article.getSynopsis()+"...";
        this.views = article.getViews();
        this.likeCount = article.getLikeCount();
        this.creationTime = article.getCreationTime();
        this.modifyTime = article.getModifyTime();
        this.userInformation = null;
        this.ifPrivate = 0;
        this.commentCount = 0;
        this.commentInformationList = null;
        this.classifyList = null;
        this.tagList = null;
    }
}
