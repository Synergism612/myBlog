package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 验证码非法
 */
public class IllegalityCodeMailException extends RuntimeException{
    public IllegalityCodeMailException() {
        super();
    }

    public IllegalityCodeMailException(String s) {
        super(s);
    }

    public IllegalityCodeMailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalityCodeMailException(Throwable cause) {
        super(cause);
    }
}
