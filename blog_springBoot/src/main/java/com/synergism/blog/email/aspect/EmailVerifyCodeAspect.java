package com.synergism.blog.email.aspect;

import com.synergism.blog.email.service.EmailService;
import com.synergism.blog.exception.custom.IllegalityCodeMailException;
import com.synergism.blog.utils.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class EmailVerifyCodeAspect {

    EmailService emailService;

    @Autowired
    EmailVerifyCodeAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @Pointcut("@annotation(com.synergism.blog.email.note.EmailVerifyCodeNote)")
    public void EmailVerifyCode() {
    }

    @Around(value = "EmailVerifyCode()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String username = "";
        String code = "";

        Object[] args = point.getArgs();
        for (Object arg : args) {
            Class<?> argClass = arg.getClass();
            Field[] fields = argClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if ("username".equals(field.getName())) {
                    username = (String) field.get(arg);
                }
                if ("code".equals(field.getName())) {
                    code = (String) field.get(arg);
                }
            }
        }
        if (StringUtil.checkStringsIfEmpty(username, code))
            throw new IllegalityCodeMailException("验证码校验异常");

        if (emailService.verifyCode(username, code))
            return point.proceed();

        throw new IllegalityCodeMailException("验证码错误");
    }
}
