package com.synergism.blog.core.user.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 作者信息
 */
@Setter
@Getter
public class Author extends UserInformation {
    //所属文章数量
    private int articleCount;
    //关注数量
    private int notableCount;
    //粉丝数量
    private int fansCount;

    public Author() {
        super();
    }
}
