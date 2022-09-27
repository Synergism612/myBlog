package com.synergism.blog.security.cryptography.note;

import java.lang.annotation.*;

/**
 * 将密码加密
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CryptographyPasswordNote {
}
