package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 时间失效
 */
public class TimeFailureException extends RuntimeException{
    public TimeFailureException() {
        super();
    }

    public TimeFailureException(String s) {
        super(s);
    }

    public TimeFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeFailureException(Throwable cause) {
        super(cause);
    }
}
