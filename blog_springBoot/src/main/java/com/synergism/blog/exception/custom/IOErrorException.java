package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 请求非法异常
 */
public class IOErrorException extends RuntimeException{

    public IOErrorException() {
        super();
    }

    public IOErrorException(String s) {
        super(s);
    }

    public IOErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOErrorException(Throwable cause) {
        super(cause);
    }
}
