package com.synergism.blog.core.email.note;


import java.lang.annotation.*;

/**
 * 验证码校验
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailCodeVerifyNote {
}
