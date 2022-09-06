package com.synergism.blog.util;

import java.lang.reflect.Type;

/**
 * 类型转换工具类
 */
public class TypeUtil {

    /**
     * 获取对象的类型
     * @param object 对象
     * @return 对象类型
     */
    public static Type getMyType(Object object) {
        return object.getClass().getGenericSuperclass();
    }

    /**
     * 将参数中的值转为对应的数组
     * @param values 多个参数
     * @return 对应的数组
     */
    public static <V> V[] asArrays(V... values) {
        return values;
    }
}
