package com.synergism.blog.core.collection.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.collection.entity.Collection;
import com.synergism.blog.core.collection.entity.CollectionInformation;
import com.synergism.blog.core.collection.mapper.CollectionMapper;
import com.synergism.blog.core.collection.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:33:18
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    private final CollectionMapper mapper;

    public CollectionServiceImpl(CollectionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<CollectionInformation> getAllCollectionInformationList() {
        return mapper.selectAllCollectionInformationList();
    }

    @Override
    public boolean save(String title, String url, String synopsis, Long groupID) {
        Collection collection = new Collection(title, url, synopsis);
        mapper.insert(collection);
        long collectionID = collection.getId();
        try {
            mapper.insertCollection(groupID, collectionID);
            return true;
        } catch (Exception e) {
            mapper.delete(new LambdaQueryWrapper<Collection>().eq(Collection::getId, collectionID));
        }
        return false;
    }

    @Override
    public boolean isExist(Long favoriteID, String href) {
        return mapper.selectOneByFavoriteIDAndHref(favoriteID, href) != null;
    }

    @Override
    public boolean remove(Long favoriteID, List<Long> collectionIDList) {
        try {
            mapper.deleteCollection(favoriteID, collectionIDList);
            mapper.delete(new LambdaQueryWrapper<Collection>().in(Collection::getId, collectionIDList));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
