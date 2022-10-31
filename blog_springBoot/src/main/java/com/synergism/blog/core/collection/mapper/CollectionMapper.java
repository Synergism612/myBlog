package com.synergism.blog.core.collection.mapper;

import com.synergism.blog.core.collection.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

    void addCollection(Long favoriteID,long collectionID);
}
