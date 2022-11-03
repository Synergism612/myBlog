package com.synergism.blog.core.article.enumeration;

/**
 * 排序方式枚举
 */
public enum ArticleSort {
    //最新发布
    creation_time,
    //最近更新
    modify_time,
    //查看最多
    views,
    //点赞最多
    like_count,
    //评论最多
    comment_count
}
