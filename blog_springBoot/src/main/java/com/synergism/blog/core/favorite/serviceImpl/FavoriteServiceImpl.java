package com.synergism.blog.core.favorite.serviceImpl;

import com.synergism.blog.core.favorite.entity.Favorite;
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

    @Autowired
    public FavoriteServiceImpl(FavoriteMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Favorite> getFavoriteListByUserID(long userID) {
        return mapper.selectListByUserID(userID);
    }
}
