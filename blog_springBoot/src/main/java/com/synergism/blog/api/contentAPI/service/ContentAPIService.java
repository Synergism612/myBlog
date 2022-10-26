package com.synergism.blog.api.contentAPI.service;

import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

import java.util.List;

public interface ContentAPIService {
    Result<Article> getArticle(long id);

    Result<Author> getAuthor(long id);

    Result<Classify> getClassify(long id);

    Result<List<Tag>> getTagList(long id);

    Result<List<CommentParent>> getCommentList(long id);
}
