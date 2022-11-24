package com.synergism.blog.core.like.serviceImpl;

import com.synergism.blog.core.like.service.LikeService;
import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final String key = "434459009467023360";

    private final CacheRedisService redisService;

    private final String articleKeyword = ":like:article:";

    private final String commentKeyword = ":like:comment:";


    @Autowired
    public LikeServiceImpl(CacheRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean isLikeArticle(String username, long articleID) {
        String hashKey = username + articleKeyword + articleID;
        return redisService.getHash(this.key, hashKey).equals(1);
    }

    @Override
    public void likeArticle(String username, long articleID, boolean state) {
        String hashKey = username + articleKeyword + articleID;
        if (state) {
            redisService.putHash(this.key, hashKey, 1);
        } else {
            redisService.putHash(this.key, hashKey, 0);
        }
    }

    @Override
    public List<Long> getLikeCommentIDList(String username) {
        String likeKeyword = username + commentKeyword;
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
        String hashKey = username + commentKeyword + commentID;
        if (state) {
            redisService.putHash(this.key, hashKey, 1);
        } else {
            redisService.putHash(this.key, hashKey, 0);
        }
    }

    @Override
    public Map<Long, Long> getArticleLikeInformation() {
        return this.getInformation(articleKeyword);
    }

    @Override
    public Map<Long, Long> getCommentLikeInformation() {
        return this.getInformation(commentKeyword);
    }

    /**
     * 该方法用于获取redis中关于点赞的详细信息
     * @param keyword 关键字
     * @return map[id,数值]
     */
    private Map<Long, Long> getInformation(String keyword) {
        Map<Long, Long> result = new HashMap<>();
        redisService.getHashKey(key).forEach(hashKey -> {
            if (hashKey.contains(keyword)) {
                Long articleID = Long.valueOf(hashKey.substring(hashKey.lastIndexOf(':') + 1));
                Long count = result.get(articleID);
                if (count == null) {
                    result.put(articleID, 0L);
                    count = 0L;
                }
                result.put(articleID, count + (Integer)redisService.getHash(key, hashKey));
            }
        });
        return result;
    }
}
