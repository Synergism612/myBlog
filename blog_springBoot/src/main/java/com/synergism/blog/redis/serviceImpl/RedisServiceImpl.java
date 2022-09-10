package com.synergism.blog.global.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
    private final int timeout = 168; //过期时间，单位小时

    @Override
    public void setValue(String key, Map<String, Object> value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.HOURS); // 这里指的是1小时后失效
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.HOURS); // 这里指的是1小时后失效
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.HOURS); // 这里指的是1小时后失效
    }

    @Override
    public void getAndSetValue(String key, String value) {
        redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.HOURS);
    }

    @Override
    public void getAndSetValue(String key, Object value) {
        redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.HOURS);

    }

    @Override
    public Object getMapValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
