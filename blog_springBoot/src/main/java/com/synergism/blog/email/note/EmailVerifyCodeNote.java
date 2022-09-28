package com.synergism.blog.email.note;


import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailVerifyCodeNote {
}
