package com.synergism.blog.core.like.service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件夹表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
public interface LikeService {

    /**
     * 是否已经点赞文章
     * @param username 账号
     * @param articleID 文章id
     * @return 已点为真，反之为假
     */
    boolean isLikeArticle(String username, long articleID);

    /**
     * 为文章点赞
     * @param username 账号
     * @param articleID 文章id
     * @param state 状态
     */
    void likeArticle(String username, long articleID,boolean state);

    /**
     * 获取点过赞的评论
     * @param username 账号
     * @return 评论id列表
     */
    List<Long> getLikeCommentIDList(String username);

    /**
     * 为评论点赞
     * @param username 账号
     * @param commentID 评论id
     * @param state 状态
     */
    void likeComment(String username, long commentID,boolean state);

    /**
     * 获取文章点赞信息
     * @return Map<文章id,点赞数>
     */
    Map<Long,Long> getArticleLikeInformation();

    /**
     * 获取文章点赞信息
     * @return Map<文章id,点赞数>
     */
    Map<Long,Long> getCommentLikeInformation();

}
