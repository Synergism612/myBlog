package com.synergism.blog.api.homepageAPI.service;

import com.synergism.blog.api.enshrineAPI.entity.AddFavoriteGroup;
import com.synergism.blog.core.favorite.entity.MyFavorite;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

import java.util.List;

public interface HomepageAPIService {

    Result<Author> getAuthor(String username);

    Result<List<MyFavorite>> getFavorite(String username);

    Result<String> saveFavorite(AddFavoriteGroup addFavoriteGroup);
}
