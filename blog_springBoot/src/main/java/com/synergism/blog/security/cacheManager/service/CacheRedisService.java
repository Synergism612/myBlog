package com.synergism.blog.security.cacheManager.service;


import java.util.List;
import java.util.Map;

public interface CacheRedisService {

   /**
    * 获取对应数据
    * @param key 主键
    * @return 数据
    */
   Object get(String key);

   /**
    * 写入数据
    * @param value 数据
    * @param time 存储时间
    * @return 主键
    */
   String put(Object value,long time);

   /**
    * 删除数据
    * @param key 主键
    */
   void remove(String... key);

   /**
    * 更新数据
    * @param key 主键
    * @param value 数据
    * @param time 存储时间
    * @return 旧数据
    */
   Object update(String key,Object value,long time);

   /**
    * 获取对应数据 hash
    * @param key 主键
    * @param hashKey 哈希键
    * @return 哈希值
    */
   Object getHash(String key,String hashKey);

   /**
    * 获取对应的哈希键 hash
    * @param key 主键
    * @return 哈希键列表
    */
   List<String> getHashKey(String key);

   /**
    * 写入数据 hash
    * @param key 主键
    * @param hashKey 哈希键
    * @param hashValue 哈希值
    * @return 旧数据
    */
   Object putHash(String key,String hashKey,Object hashValue);
}
