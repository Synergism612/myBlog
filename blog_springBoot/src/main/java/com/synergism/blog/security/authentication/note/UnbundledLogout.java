package com.synergism.blog.security.authentication.note;


import java.lang.annotation.*;

/**
 * 用户登出时解绑会话
 * 并且删除登录信息
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnbundledLogout {
}
