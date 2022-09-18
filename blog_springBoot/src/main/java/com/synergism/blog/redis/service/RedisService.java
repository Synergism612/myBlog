package com.synergism.blog.redis.service;

import java.util.Map;

public interface RedisService {
    // 加入元素
    void setValue(String key, Map<String, Object> value);
    // 加入元素
    void setValue(String key, String value);
    // 加入元素
    void setValue(String key, Object value);
    // 更新元素
    void getAndSetValue(String key, String value);
    // 更新元素
    void getAndSetValue(String key, Object value);
    // 获取元素
    Object getMapValue(String key);
    // 获取元素
    Object getValue(String key);

    //邮箱验证码接口
    void setEmail(String email,Map<String,String> code);
}
