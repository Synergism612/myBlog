package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentInformation extends Comment {
    private String username;
    private String icon;
    private String nickname;

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
        this.username = commentInformation.username;
        this.icon = commentInformation.icon;
        this.nickname = commentInformation.nickname;
    }
}
