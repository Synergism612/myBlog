package com.synergism.blog.security.cacheManager.service;


public interface cacheRedisService {

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

}
