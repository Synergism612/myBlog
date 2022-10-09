package com.synergism.blog.api.index.entity;


import com.synergism.blog.core.article.entity.Article;

public class ArticleInformation {
    //作者昵称
    private String userName;
    //是否私有
    private int ifPrivate;
    //文章标题
    private String articleTitle;
    //文章内容
    private String articleBody;
    //文章浏览量
    private String views;
    //文章点赞量
    private String likeCount;

    public ArticleInformation(String userName, int ifPrivate, String articleTitle, String articleBody, String views, String likeCount) {
        this.userName = userName;
        this.ifPrivate = ifPrivate;
        this.articleTitle = articleTitle;
        this.articleBody = articleBody;
        this.views = views;
        this.likeCount = likeCount;
    }

    public ArticleInformation(Article article,String userName,int ifPrivate){
        this.userName = userName;
        this.ifPrivate = ifPrivate;
        this.articleTitle = article.getTitle();
        this.articleBody = article.getBody();
        this.views = article.getViews();
        this.likeCount = article.getLikeCount();
    }
}
