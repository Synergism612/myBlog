package com.synergism.blog.api.contentAPI.entity;

import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Content {
    private Article article;

    private UserInformation userInformation;

    private Classify classify;

    private List<Tag> tagList;

    private final List<CommentInformation> commentInformationList;

    public Content(Article article, UserInformation userInformation, Classify classify, List<Tag> tagList, List<CommentInformation> commentInformationList) {
        this.article = article;
        this.userInformation = userInformation;
        this.classify = classify;
        this.tagList = tagList;
        this.commentInformationList = commentInformationList;
    }
}
