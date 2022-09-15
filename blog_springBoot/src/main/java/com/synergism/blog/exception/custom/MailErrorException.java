package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 邮箱服务异常
 */
public class MailErrorException extends RuntimeException{

    public MailErrorException() {
        super();
    }

    public MailErrorException(String s) {
        super(s);
    }

    public MailErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailErrorException(Throwable cause) {
        super(cause);
    }
}
