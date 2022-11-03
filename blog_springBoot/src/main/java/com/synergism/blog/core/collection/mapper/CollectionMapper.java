package com.synergism.blog.core.collection.mapper;

import com.synergism.blog.core.collection.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.collection.entity.CollectionInformation;
import com.synergism.blog.result.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 收藏信息表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:33:18
 */
@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

    List<CollectionInformation> selectAllCollectionInformationList();

    void insertCollection(Long favoriteID, long collectionID);

    Collection selectOneByFavoriteIDAndHref(Long favoriteID, String href);

    void deleteCollection(Long favoriteID, List<Long> collectionIDList);

}
