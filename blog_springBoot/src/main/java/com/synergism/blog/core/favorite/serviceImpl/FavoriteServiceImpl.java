package com.synergism.blog.core.favorite.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.favorite.entity.Collection;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.favorite.mapper.CollectionMapper;
import com.synergism.blog.core.favorite.mapper.FavoriteMapper;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean isExist(Long favoriteID) {
        return this.getOne(new LambdaQueryWrapper<Favorite>().eq(Favorite::getId, favoriteID)) != null;
    }

    @Override
    public boolean save(String title, String href, String synopsis, Long favoriteID) {
        Collection collection = new Collection(title, href, synopsis);
        collectionMapper.insert(collection);
        if (collection.getId()==null) return false;
        long collectionID = collection.getId();
        try {
            collectionMapper.bundle(collectionID, favoriteID);
            return true;
        } catch (Exception e) {
            collectionMapper.delete(new LambdaQueryWrapper<Collection>().eq(Collection::getId, collectionID));
        }
        return false;
    }

    @Override
    public boolean isExist(Long favoriteID, String href) {
        return mapper.selectCollectionInFavoriteHref(favoriteID, href) != null;
    }

    @Override
    public boolean remove(Long favoriteID, List<Long> collectionIDList) {
        try {
            collectionMapper.unbundled(favoriteID, collectionIDList);
            collectionMapper.delete(new LambdaQueryWrapper<Collection>().in(Collection::getId, collectionIDList));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Favorite> getListByUsername(String username) {
        List<Favorite> result = mapper.selectListByUsername(username);
        return result.size() == 0 ? null : result;
    }

    @Override
    public boolean save(String name, String annotation, Integer ifPrivate, Long userID) {
        Favorite favorite = new Favorite(null,name,annotation,ifPrivate);
        mapper.insert(favorite);
        if (favorite.getId()==null) return false;
        long favoriteID = favorite.getId();
        try {
            mapper.bundle(favoriteID, userID);
            return true;
        } catch (Exception e) {
            mapper.delete(new LambdaQueryWrapper<Favorite>().eq(Favorite::getId, favoriteID));
        }
        return false;
    }

}
