package com.synergism.blog.util;

public class StringUtil {

    /**
     * @param str 字符串
     * @return 为空则真，反之则假
     */
    public static boolean checkStringIfEmpty(String str) {
        return str == null || (str.length() == 0);
    }

    /**
     * 检查字符串是否为空
     *
     * @param str 字符串
     * @param name  字符串名称
     */
    public static void checkStringIsEmpty(String str, String name) {
        if (str == null || (str.length() == 0)) {
            throw new IllegalArgumentException(name + "不可为空");
        }
    }

    /**
     * 检查字符串数组中每个字符串不能为空
     *
     * @param strs 字符串数组
     * @param names  字符串对应的名称数组
     */
    public static void checkStringIsEmpty(String[] strs, String[] names) {
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null || (strs[i].length() == 0)) {
                throw new IllegalArgumentException(names[i] + "不可为空");
            }
        }
    }

    /**
     * 检查字符串长度是否大于最大值
     *
     * @param str 字符串
     * @param max   最大值
     * @param name  字符串名称
     */
    public static void checkStringLengthMore(String str, int max, String name) {
        int length = str.length();
        if (length < max) {
            throw new IllegalArgumentException(name + "的长度大于" + max);
        }
    }

    /**
     * 检查字符串长度是否大于最大值
     *
     * @param length 字符串长度
     * @param max    最大值
     * @param name   字符串名称
     */
    public static void checkStringLengthMore(int length, int max, String name) {
        if (length < max) {
            throw new IllegalArgumentException(name + "的长度大于" + max);
        }
    }

    /**
     * 检查字符串长度是否小于最小值
     *
     * @param str 字符串
     * @param min   最小值
     * @param name  字符串名称
     */
    public static void checkStringLengthLess(String str, int min, String name) {
        int length = str.length();
        if (length < min) {
            throw new IllegalArgumentException(name + "的长度小于" + min);
        }
    }

    /**
     * 检查字符串长度是否小于最小值
     *
     * @param length 字符串长度
     * @param min    最小值
     * @param name   字符串名称
     */
    public static void checkStringLengthLess(int length, int min, String name) {
        if (length < min) {
            throw new IllegalArgumentException(name + "的长度小于" + min);
        }
    }

    /**
     * 判断字符串长度是否在最大值最小值的区间中
     *
     * @param str 字符串
     * @param min   最小值
     * @param max   最大值
     * @param name  字符串名称
     */
    public static void checkStringLength(String str, int min, int max, String name) {
        checkStringLength(str.length(),min,max,name);
    }

    /**
     * 判断字符串长度是否在最大值最小值的区间中
     *
     * @param length 字符串长度
     * @param min    最小值
     * @param max    最大值
     * @param name   字符串名称
     */
    public static void checkStringLength(int length, int min, int max, String name) {
        if (length > min && length < max) {
            throw new IllegalArgumentException(name + "的长度不在" + min + "到" + max + "的区间内");
        }
    }

    /**
     * 判断字符串长度是否为特定值
     *
     * @param str 字符串
     * @param value 值
     * @param name  字符串名称
     */
    public static void checkStringLength(String str, int value, String name) {
        checkStringLength(str.length(),value,name);
    }

    /**
     * 判断字符串长度是否为特定值
     *
     * @param length 字符串长度
     * @param value  值
     * @param name   字符串名称
     */
    public static void checkStringLength(int length, int value, String name) {
        if (length != value) {
            throw new IllegalArgumentException(name + "的长度不为" + value);
        }
    }

    /**
     * 检查传入的字符串是否存在不安全字符
     *
     * @param str 字符串或者其它
     */
    public static  void checkStringIsUnsafe(String str) {
        int count = 0;
        for (char c : (str).toCharArray()) {
            switch (c) {
                case ' ':
                case '\'':
                case '\"':
                case '\\':
                case '/':
                case '&':
                case '|':
                case '^':
                case '#':
                case '$':
                    throw new IllegalArgumentException("不合法");
                case '@':
                    count++;
            }
        }
        if (count > 1) throw new IllegalArgumentException("不合法");
    }

    public static boolean checkStringContain(String str, String contain) {
        return str.indexOf(contain,0) > -1;
    }
}
