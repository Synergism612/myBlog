package com.synergism.blog.security.cacheManager.serviceImpl;

import com.synergism.blog.security.cacheManager.service.cacheRedisService;
import com.synergism.blog.security.cacheManager.utils.SnowflakeIdWorker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.synergism.blog.utils.StringUtil.asString;

@Service
public class CacheRedisServiceImpl implements cacheRedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public String put(Object value,long time) {
        String key = asString(new SnowflakeIdWorker(0, 0).nextId());
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
        Object result = this.get(key);
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }
}
