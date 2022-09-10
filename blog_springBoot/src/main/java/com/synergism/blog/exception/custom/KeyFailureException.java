package com.synergism.blog.global.exception.custom;

/**
 * 自定义异常
 * 请求非法异常
 */
public class KeyFailureException extends RuntimeException{

    public KeyFailureException() {
        super();
    }

    public KeyFailureException(String s) {
        super(s);
    }

    public KeyFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyFailureException(Throwable cause) {
        super(cause);
    }
}
