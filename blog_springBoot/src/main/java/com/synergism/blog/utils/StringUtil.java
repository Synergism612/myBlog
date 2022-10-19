package com.synergism.blog.utils;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 检查字符串是否为空
     *
     * @param str 字符串
     * @return 为空则真，反之则假
     */
    public static boolean isEmpty(String str) {
        return str == null || (str.length() == 0);
    }

    /**
     * 检查字符串数组是否有字符串为空
     *
     * @param strings 字符串数组
     * @return 存在则真，不存在则假
     */
    public static boolean isEmpty(String... strings) {
        for (String str : strings) {
            if (isEmpty(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查字符串是否为空
     *
     * @param str 字符串
     * @param name  字符串名称
     */
    public static void ifEmpty(String str, String name) {
        if (str == null || (str.length() == 0)) {
            throw new IllegalArgumentException(name + "不可为空");
        }
    }

    /**
     * 检查字符串数组中每个字符串不能为空
     *
     * @param strings 字符串数组
     * @param names  字符串对应的名称数组
     */
    public static void ifEmpty(String[] strings, String[] names) {
        for (int i = 0; i < strings.length; i++) {
            ifEmpty(strings[i],names[i]);
        }
    }

    /**
     * 检查传入的字符串是否存在不安全字符
     *
     * @param strings 字符串
     */
    public static  void checkStringIsUnsafe(String... strings) {
        for (String str : strings) {
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
                    case '-':
                        count++;
                }
            }
            if (count > 1) throw new IllegalArgumentException("不合法");
        }
    }

        /**
         * 转为字符串
         * @param value 值
         * @return 字符串
         */
    public static <V> String asString(V value){
        return value.toString();
    }
}
