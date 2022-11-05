package com.synergism.blog.core.favorite.mapper;

import com.synergism.blog.core.favorite.entity.Collection;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 收藏夹表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:32:37
 */
@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {

    /**
     * 查询该用户的收藏夹信息
     * @param username 账号
     * @return 收藏夹信息列表
     */
    List<Favorite> selectListByUsername(String username);

    /**
     * 绑定新的收藏夹
     * @param favoriteID 收藏夹id
     * @param userID 用户id
     */
    void bundle(long favoriteID,long userID);

    /**
     * 查询全部收藏夹详细信息
     * @return 收藏夹详细信息列表
     */
    List<FavoriteInformation> selectAllFavoriteInformationList();

    /**
     * 查询该用户的收藏夹详细信息
     * @param username 账号
     * @return 收藏夹详细信息列表
     */
    List<FavoriteInformation> selectFavoriteInformationList(String username);

    /**
     * 查询收藏夹中href相同的收藏
     * 具有唯一性
     * @param favoriteID 收藏夹
     * @param href 链接
     * @return 收藏信息
     */
    Collection selectCollectionInFavoriteHref(Long favoriteID, String href);

    /**
     * 解绑收藏夹
     * @param userID 用户id
     * @param favoriteID 收藏夹id
     */
    void unbundled(Long userID, Long favoriteID);
}
