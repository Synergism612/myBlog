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

    List<Favorite> selectListByUsername(String username);

    void bundle(long userID, long favoriteID);

    List<FavoriteInformation> selectAllFavoriteInformationList();

    List<FavoriteInformation> selectFavoriteInformationList(String username);

    Collection selectCollectionInFavoriteHref(Long favoriteID, String href);

}
