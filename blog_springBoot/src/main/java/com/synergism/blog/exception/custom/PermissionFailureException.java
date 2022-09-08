package com.synergism.blog.exception.custom;

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
