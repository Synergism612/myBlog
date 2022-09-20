package com.synergism.blog.redis.service;

import com.synergism.blog.email.entity.CodeMail;

import java.util.Map;

public interface RedisService {
    /**
     * 添加元素
     * @param key 键名
     * @param value 键值[Map]
     */
    void setValue(String key, Map<String, Object> value);

    /**
     * 添加元素
     * @param key 键名
     * @param value 键值[String]
     */
    void setValue(String key, String value);
    /**
     * 添加元素
     * @param key 键名
     * @param value 键值[Object]
     */
    void setValue(String key, Object value);

    /**
     * 更新元素
     * @param key 键名
     * @param value 键值[String]
     */
    void getAndSetValue(String key, String value);
    /**
     * 更新元素
     * @param key 键名
     * @param value 键值[Object]
     */
    void getAndSetValue(String key, Object value);

    /**
     * 获取元素
     * @param key 键名
     * @return 键值[Object]
     */
    Object getValue(String key);

    /**
     * 邮箱验证码接口
     * @param email 邮箱
     * @param codeMail 验证码信息
     */
    void setEmail(String email, CodeMail codeMail);
}
