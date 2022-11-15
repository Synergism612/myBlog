package com.synergism.blog.core.favorite.serviceImpl;

import com.synergism.blog.core.favorite.entity.Collection;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.favorite.mapper.CollectionMapper;
import com.synergism.blog.core.favorite.mapper.FavoriteMapper;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收藏夹表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:32:37
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    private final FavoriteMapper mapper;
    private final CollectionMapper collectionMapper;

    @Autowired
    public FavoriteServiceImpl(FavoriteMapper mapper, CollectionMapper collectionMapper) {
        this.mapper = mapper;
        this.collectionMapper = collectionMapper;
    }

    @Override
    public List<FavoriteInformation> getAllFavoriteInformationList() {
        return mapper.selectAllFavoriteInformationList();
    }

    @Override
    public List<FavoriteInformation> getFavoriteInformationListByUsername(String username) {
        List<FavoriteInformation> result = mapper.selectFavoriteInformationList(username);
        return result.size() == 0 ? null : result;
    }

    @Override
    public boolean isExist(String username,long favoriteID) {
        return mapper.selectOneByUsernameAndFavoriteID(username,favoriteID) != null;
    }

    @Override
    @Transactional
    public void save(String title, String href, String synopsis, long favoriteID) {
        Collection collection = new Collection(title, href, synopsis);
        collectionMapper.insert(collection);
        collectionMapper.bundle(collection.getId(), favoriteID);
    }

    @Override
    public boolean isExist(long favoriteID, String href) {
        return mapper.selectCollectionInFavoriteHref(favoriteID, href) != null;
    }

    @Override
    @Transactional
    public void remove(long favoriteID, List<Long> collectionIDList) {
        collectionMapper.unbundled(favoriteID, collectionIDList);
        collectionMapper.deleteBatchIds(collectionIDList);
    }

    @Override
    public List<Favorite> getListByUsername(String username) {
        List<Favorite> result = mapper.selectListByUsername(username);
        return result.size() == 0 ? null : result;
    }

    @Override
    @Transactional
    public void save(String name, String annotation, Integer ifPrivate, Long userID) {
        Favorite favorite = new Favorite(null, name, annotation, ifPrivate);
        mapper.insert(favorite);
        long favoriteID = favorite.getId();
            mapper.bundle(favoriteID, userID);
    }

    @Override
    @Transactional
    public void remove(Long userID, Long favoriteID) {
        mapper.unbundled(userID, favoriteID);
        mapper.deleteById(favoriteID);
    }

}
