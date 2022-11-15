package com.synergism.blog.core.favorite.service;

import com.synergism.blog.core.favorite.entity.Favorite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.result.Result;

import java.util.List;

/**
 * <p>
 * 收藏夹表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:32:37
 */
public interface FavoriteService extends IService<Favorite> {

    /**
     * 获取全部收藏夹
     * @return 收藏夹信息列表
     */
    List<FavoriteInformation> getAllFavoriteInformationList();

    /**
     * 获取用户所属的收藏夹
     * @param username 账号
     * @return 收藏夹信息列表
     */
    List<FavoriteInformation> getFavoriteInformationListByUsername(String username);

    /**
     * 是否存在收藏夹
     * @param favoriteID 收藏夹id
     * @return 存在为真，反之为夹
     */
    boolean isExist(Long favoriteID);

    /**
     * 保存新的收藏
     * @param title 标题
     * @param href 链接
     * @param synopsis 摘要
     * @param favoriteID 收藏夹id
     */
    void save(String title, String href, String synopsis, long favoriteID);

    /**
     * 收藏夹中是否存在该收藏
     * @param favoriteID 收藏夹id
     * @param href 收藏的链接
     * @return 存在为真，反之为假
     */
    boolean isExist(long favoriteID, String href);

    /**
     * 删除收藏
     * @param favoriteID 收藏夹id
     * @param collectionIDList 收藏id列表
     */
    void remove(long favoriteID, List<Long> collectionIDList);

    /**
     * 获取用户的收藏夹信息
     * @param username 账号
     * @return 收藏夹信息列表
     */
    List<Favorite> getListByUsername(String username);

    /**
     * 保存新的收藏夹
     * @param name 收藏夹名称
     * @param annotation 注释
     * @param ifPrivate 公开/私密
     * @param userID 用户id
     */
    void save(String name, String annotation, Integer ifPrivate, Long userID);

    /**
     * 删除收藏夹
     * @param userID 用户id
     * @param favoriteID 收藏夹id
     */
    void remove(Long userID,Long favoriteID);
}
