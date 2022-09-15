package com.synergism.blog.exception.custom;

/**
 * 自定义异常
 * 参数校验异常
 */
public class PermissionFailureException extends RuntimeException{
    public PermissionFailureException() {
        super();
    }

    public PermissionFailureException(String s) {
        super(s);
    }

    public PermissionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionFailureException(Throwable cause) {
        super(cause);
    }
}
