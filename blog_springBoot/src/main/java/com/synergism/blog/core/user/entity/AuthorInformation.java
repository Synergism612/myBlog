package com.synergism.blog.core.user.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorInformation {
    private int articleCount;
    private int notableCount;
    private int fansCount;

    public AuthorInformation(int articleCount, int notableCount, int fansCount) {
        this.articleCount = articleCount;
        this.notableCount = notableCount;
        this.fansCount = fansCount;
    }
}
