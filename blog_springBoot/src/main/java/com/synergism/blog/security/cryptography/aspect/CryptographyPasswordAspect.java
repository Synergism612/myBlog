package com.synergism.blog.security.cryptography.aspect;

import com.synergism.blog.security.cryptography.service.CryptographyService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class CryptographyPasswordAspect {

    CryptographyService cryptographyService;

    @Autowired
    CryptographyPasswordAspect(CryptographyService cryptographyService) {
        this.cryptographyService = cryptographyService;
    }

    @Pointcut(value = "@annotation(com.synergism.blog.security.cryptography.note.CryptographyPasswordNote)")
    public void CryptographyPassword(){}

    @Around(value = "CryptographyPassword()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (Object arg : args) {
            Class<?> argsClass = arg.getClass();
            Field[] fields = argsClass.getDeclaredFields();
            for (Field field : fields) {
                if ("password".equals(field.getName())){
                    field.setAccessible(true);
                    String value =  (String) field.get(arg);
                    field.set(arg,cryptographyService.MySQLEncrypt(value));
                }
            }
        }
        return point.proceed(args);
    }
}
