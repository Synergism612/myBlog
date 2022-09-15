package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 雪花算法异常
 */
public class SnowFailException extends RuntimeException{
    public SnowFailException() {
        super();
    }

    public SnowFailException(String s) {
        super(s);
    }

    public SnowFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SnowFailException(Throwable cause) {
        super(cause);
    }
}
