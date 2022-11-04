package com.synergism.blog.core.favorite.mapper;

import com.synergism.blog.core.favorite.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    /**
     * 绑定新的收藏
     * @param collectionID 收藏id
     * @param favoriteID 收藏夹id
     */
    void bundle( long collectionID,Long favoriteID);

    /**
     * 解绑收藏
     * @param favoriteID 收藏夹id
     * @param collectionIDList 收藏id列表
     */
    void unbundled(Long favoriteID, List<Long> collectionIDList);
}
