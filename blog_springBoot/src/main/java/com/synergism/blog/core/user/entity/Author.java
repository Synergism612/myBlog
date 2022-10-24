package com.synergism.blog.core.user.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author extends UserInformation {
    private int articleCount;
    private int notableCount;
    private int fansCount;

    public Author(UserInformation userInformation,AuthorInformation authorInformation) {
        super(userInformation);
        this.articleCount = authorInformation.getArticleCount();
        this.notableCount = authorInformation.getNotableCount();
        this.fansCount = authorInformation.getFansCount();
    }
}
