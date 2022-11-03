package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论详细信息
 */
@Getter
@Setter
public class CommentInformation extends Comment {
    //所属用户头像
    private String icon;
    //所属用户账号
    private String username;
    //所属用户昵称
    private String nickname;
    //所属文章id
    private Long articleID;

    /**
     * 空参构造器
     * 不可删除
     * 在Mapper实现层中使用了List<CommentInformation>类型
     * 故而Mybatis将会调用该构造器进行初始化列表操作
     * 删除将会导致列表无法初始化抛出类型不匹配异常
     */
    public CommentInformation(){
        super();
    }

    public CommentInformation(CommentInformation commentInformation) {
        super(commentInformation);
        this.icon = commentInformation.icon;
        this.username = commentInformation.username;
        this.nickname = commentInformation.nickname;
        this.articleID = commentInformation.getArticleID();
    }
}
