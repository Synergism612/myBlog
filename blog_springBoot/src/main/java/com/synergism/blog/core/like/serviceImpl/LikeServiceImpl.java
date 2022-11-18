package com.synergism.blog.core.like.serviceImpl;

import com.synergism.blog.core.like.service.LikeService;
import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final String key = "434459009467023360";

    private final CacheRedisService redisService;

    @Autowired
    public LikeServiceImpl(CacheRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean isLikeArticle(String username, long articleID) {
        String hashKey = username + ":like:article:" + articleID;
        return redisService.getHash(this.key, hashKey).equals(1);
    }

    @Override
    public void likeArticle(String username, long articleID, boolean state) {
        String hashKey = username + ":like:article:" + articleID;
        if (state) {
            redisService.putHash(this.key, hashKey, 1);
        } else {
            redisService.putHash(this.key, hashKey, 0);
        }
    }

    @Override
    public List<Long> getLikeCommentIDList(String username) {
        String likeKeyword = username + ":like:comment:";
        List<String> hashKeyList = redisService.getHashKey(this.key);
        hashKeyList = hashKeyList.stream().filter(hashKey ->
                hashKey.contains(likeKeyword)
                        && redisService.getHash(this.key, hashKey).equals(1)
        ).collect(Collectors.toList());
        return hashKeyList.stream().map(hashKey ->
                Long.valueOf(hashKey.substring(hashKey.length() - 1))
        ).collect(Collectors.toList());
    }

    @Override
    public void likeComment(String username, long commentID, boolean state) {
        String hashKey = username + ":like:comment:" + commentID;
        if (state) {
            redisService.putHash(this.key, hashKey, 1);
        } else {
            redisService.putHash(this.key, hashKey, 0);
        }
    }
}
