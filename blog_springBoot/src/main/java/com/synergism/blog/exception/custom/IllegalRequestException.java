package com.synergism.blog.exception.custom;

/**
 * 请求非法异常
 */
public class IllegalRequestException extends RuntimeException{

    public IllegalRequestException() {
        super();
    }

    public IllegalRequestException(String s) {
        super(s);
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }
}
