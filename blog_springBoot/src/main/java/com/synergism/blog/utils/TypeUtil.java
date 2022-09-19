package com.synergism.blog.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class TypeUtil {

    /**
     * 将复数个值合成值数组
     *
     * @param values 值
     * @return 值数组
     */
    @SafeVarargs
    public static <V> V[] asArray(V... values) {
        return values;
    }

    /**
     * 判断对象是空并抛出异常
     * @param objects 对象
     */
    @SafeVarargs
    public static <O> void isNull(O... objects) {
        for (O object : objects) {
            if (object == null)
                throw new IllegalArgumentException("娶不到对象");
            Class<?> clazz = object.getClass(); // 得到类对象
            Field[] fields = clazz.getDeclaredFields(); // 得到所有属性
            boolean flag = true; //定义返回结果，默认为true
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue;
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

    /**
     * 判断对象是否为空
     * @param objects 对象
     * @return 空为真，否为假
     */
    @SafeVarargs
    public static <O> boolean ifNull(O... objects) {
        boolean flag = false; //定义返回结果，默认为true
        for (O object : objects) {
            if (object == null) {
                flag = true;
                break;
            }
            Class<?> clazz = object.getClass(); // 得到类对象
            Field[] fields = clazz.getDeclaredFields(); // 得到所有属性
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = null;
                try {
                    fieldValue = field.get(object); //得到属性值
                } catch (Exception e) {
                    flag = true;
                }
                if (fieldValue != null) {  //只要有一个属性值不为null 就返回false 表示对象不为null
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 将字符串数组转为字符串 用逗号隔开
     * @param array
     * @return
     */
    public static String arrayToString(String[] array){
        String result = "";

        for (String s : array) {
            result+=s+"·";
        }
        return result.substring(0,result.length()-1);
    }
}
