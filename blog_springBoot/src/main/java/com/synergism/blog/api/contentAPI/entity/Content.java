package com.synergism.blog.api.contentAPI.entity;

import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Content {
    private Article article;

    private Author author;

    private Classify classify;

    private List<Tag> tagList;

    private final List<CommentParent> commentParentList;

    public Content(Article article, Author author, Classify classify, List<Tag> tagList, List<CommentParent> commentParentList) {
        this.article = article;
        this.author = author;
        this.classify = classify;
        this.tagList = tagList;
        this.commentParentList = commentParentList;
    }
}
