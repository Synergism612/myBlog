package com.synergism.blog.security.authentication.note;

import java.lang.annotation.*;

/**
 * 登录后绑定到会话
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BundleLogin {
}
