package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 注册失败
 */
public class RegisterFailException extends RuntimeException{
    public RegisterFailException() {
        super();
    }

    public RegisterFailException(String s) {
        super(s);
    }

    public RegisterFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterFailException(Throwable cause) {
        super(cause);
    }
}
