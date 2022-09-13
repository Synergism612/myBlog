package com.synergism.blog.utils;

import java.lang.reflect.Field;

public class TypeUtil {
    public static <V> V[] asArray(V... values) {
        return values;
    }

    @SafeVarargs
    public static <O> void isNull(O... objects) {
        for (O object : objects) {
            if (object == null)
                throw new IllegalArgumentException("娶不到对象");
            Class clazz = object.getClass(); // 得到类对象
            Field[] fields = clazz.getDeclaredFields(); // 得到所有属性
            boolean flag = true; //定义返回结果，默认为true
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = null;
                try {
                    fieldValue = field.get(object); //得到属性值
                } catch (Exception e) {
                    throw new IllegalArgumentException("娶不到对象");
                }
                if (fieldValue != null) {  //只要有一个属性值不为null 就返回false 表示对象不为null
                    flag = false;
                    break;
                }
            }
            if (flag)
                throw new IllegalArgumentException("娶不到对象");
        }
    }
}
