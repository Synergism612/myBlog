package com.synergism.blog.core.article.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
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
     //作者昵称
    private String userName;
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
}
