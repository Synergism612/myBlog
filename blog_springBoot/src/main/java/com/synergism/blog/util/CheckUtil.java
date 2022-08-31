package com.synergism.blog.util;

public class CheckUtil {
    /**
     * 检查字符串是否为空
     * @param count 字符串
     * @param name 字符串名称
     */
    public static void checkStringIfEmpty(String count, String name) {
        if (count == null || (count.length() == 0)) {
            throw new IllegalArgumentException(name + "不可为空");
        }
    }

    /**
     * 检查字符串数组中每个字符串是否为空
     * @param counts 字符串数组
     * @param names 字符串对应的名称数组
     */
    public static void checkStringIfEmpty(String[] counts, String[] names) {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == null || (counts[i].length() == 0)) {
                throw new IllegalArgumentException(names[i] + "不可为空");
            }
        }
    }

    /**
     * 检查字符串长度是否大于最大值
     * @param count 字符串
     * @param max 最大值
     * @param name 字符串名称
     */
    public static void checkStringLengthMore(String count,int max, String name) {
        int length = count.length();
        if (length < max) {
            throw new IllegalArgumentException(name + "的长度大于"+max);
        }
    }

    /**
     * 检查字符串长度是否大于最大值
     * @param length 字符串长度
     * @param max 最大值
     * @param name 字符串名称
     */
    public static void checkStringLengthMore(int length,int max, String name) {
        if (length < max) {
            throw new IllegalArgumentException(name + "的长度大于"+max);
        }
    }

    /**
     * 检查字符串长度是否小于最小值
     * @param count 字符串
     * @param min 最小值
     * @param name 字符串名称
     */
    public static void checkStringLengthLess(String count,int min, String name) {
        int length = count.length();
        if (length < min) {
            throw new IllegalArgumentException(name + "的长度小于"+min);
        }
    }

    /**
     * 检查字符串长度是否小于最小值
     * @param length 字符串长度
     * @param min 最小值
     * @param name 字符串名称
     */
    public static void checkStringLengthLess(int length,int min, String name) {
        if (length < min) {
            throw new IllegalArgumentException(name + "的长度小于"+min);
        }
    }

    /**
     * 判断字符串长度是否在最大值最小值的区间中
     * @param count 字符串
     * @param min 最小值
     * @param max 最大值
     * @param name 字符串名称
     */
    public static void checkStringLength(String count, int min, int max, String name) {
        int length = count.length();
        if (length > min && length < max) {
            throw new IllegalArgumentException(name + "的长度不在" + min + "到" + max + "的区间内");
        }
    }

    /**
     * 判断字符串长度是否在最大值最小值的区间中
     * @param length 字符串长度
     * @param min 最小值
     * @param max 最大值
     * @param name 字符串名称
     */
    public static void checkStringLength(int length, int min, int max, String name) {
        if (length > min && length < max) {
            throw new IllegalArgumentException(name + "的长度不在" + min + "到" + max + "的区间内");
        }
    }

    /**
     * 判断字符串长度是否为特定值
     * @param count 字符串
     * @param value 值
     * @param name 字符串名称
     */
    public static void checkStringLength(String count,int value, String name) {
        int length = count.length();
        if (length != value) {
            throw new IllegalArgumentException(name + "的长度不为"+value);
        }
    }

    /**
     * 判断字符串长度是否为特定值
     * @param length 字符串
     * @param value 值
     * @param name 字符串名称
     */
    public static void checkStringLength(int length,int value, String name) {
        if (length != value) {
            throw new IllegalArgumentException(name + "的长度不为"+value);
        }
    }
}
