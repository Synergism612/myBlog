package com.synergism.blog.api.email.utils;

import java.util.Random;

public class CodeUtil {
    public static String code(){
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        do {
            result.append(random.nextInt(10));
        } while (result.length() <= 4);
        return result.toString();
    }
}
