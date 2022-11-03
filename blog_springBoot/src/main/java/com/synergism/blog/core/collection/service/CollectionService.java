package com.synergism.blog.core.collection.service;

import com.synergism.blog.core.collection.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.collection.entity.CollectionInformation;
import com.synergism.blog.result.Result;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:33:18
 */
public interface CollectionService extends IService<Collection> {

    List<CollectionInformation> getAllCollectionInformationList();

    boolean save(String title, String url, String synopsis, Long groupID);

    boolean isExist(Long favoriteID, String href);

    boolean remove(Long favoriteID, List<Long> collectionIDList);
}
