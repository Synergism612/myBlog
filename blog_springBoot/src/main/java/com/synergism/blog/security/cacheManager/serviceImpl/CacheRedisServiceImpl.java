package com.synergism.blog.security.cacheManager.serviceImpl;

import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import com.synergism.blog.security.cacheManager.utils.SnowflakeIdWorker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CacheRedisServiceImpl implements CacheRedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public String put(Object value, long time) {
        String key = String.valueOf(new SnowflakeIdWorker(0, 0).nextId());
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
                return key;
            }
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void remove(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    @Override
    public Object update(String key, Object value, long time) {
        if (key.isEmpty()) {
            return "";
        }
        Object result = this.get(key);
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        return result;
    }

    @Override
    public Object getHash(String key, String hashKey) {
        return key == null ? null : redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public List<String> getHashKey(String key) {
        return key == null ? null : redisTemplate.opsForHash().keys(key).stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public Object putHash(String key, String hashKey, Object hashValue) {
        if (key.isEmpty()) {
            return "";
        }
        Object result = this.getHash(key,hashKey);
            redisTemplate.opsForHash().put(key,hashKey,hashValue);
        return result;
    }
}
