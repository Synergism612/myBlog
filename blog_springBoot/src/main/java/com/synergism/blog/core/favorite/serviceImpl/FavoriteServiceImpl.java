package com.synergism.blog.core.favorite.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.favorite.mapper.FavoriteMapper;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    public FavoriteServiceImpl(FavoriteMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<FavoriteInformation> getAllFavoriteInformationList() {
        return mapper.selectAllFavoriteInformationList();
    }

    @Override
    public List<FavoriteInformation> getFavoriteListByUsername(String username) {
        List<FavoriteInformation> result = this.getAllFavoriteInformationList()
                .stream()
                .filter(favoriteInformation ->
                        favoriteInformation
                                .getUsername()
                                .equals(username))
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result;
    }

    @Override
    public boolean isExist(Long favoriteID) {
        return this.getOne(new LambdaQueryWrapper<Favorite>().eq(Favorite::getId, favoriteID)) != null;
    }

}
