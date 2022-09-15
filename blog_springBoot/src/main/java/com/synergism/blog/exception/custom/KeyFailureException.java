package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 密钥非法异常
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
