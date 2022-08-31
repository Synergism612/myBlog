package com.synergism.blog.util;

import java.lang.reflect.Type;
import java.util.Arrays;

public class TypeUtil {

    public static Type getMyType(Object object) {
        return object.getClass().getGenericSuperclass();
    }

    public static <V> V[] asArrays(V... values) {
        return values;
    }
}
