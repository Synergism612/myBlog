package com.synergism.blog.core.favorite.service;

import com.synergism.blog.core.favorite.entity.Favorite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;

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

    List<FavoriteInformation> getAllFavoriteInformationList();

    List<FavoriteInformation> getFavoriteListByUsername(String username);

    boolean isExist(Long favoriteID);

}
