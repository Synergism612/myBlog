package com.synergism.blog.utils;

public class NumberUtil<N extends Number> {

    public boolean checkIfNegative(N... numbers) {
        for (N number : numbers) {
            if (number.doubleValue()<0)
                return true;
        }
        return false;
    }

    public void checkIsNegative(N... numbers) {

    }
}
