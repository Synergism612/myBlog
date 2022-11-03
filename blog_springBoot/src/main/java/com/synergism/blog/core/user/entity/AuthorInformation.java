package com.synergism.blog.core.user.entity;

/**
 * 作者除用户信息外的其它信息
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorInformation {
    //所属文章数量
    private int articleCount;
    //关注数量
    private int notableCount;
    //粉丝数量
    private int fansCount;

    public AuthorInformation(int articleCount, int notableCount, int fansCount) {
        this.articleCount = articleCount;
        this.notableCount = notableCount;
        this.fansCount = fansCount;
    }
}
