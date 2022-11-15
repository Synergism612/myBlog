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
public class CheckPassword {

    CryptographyService cryptographyService;

    @Autowired
    CheckPassword(CryptographyService cryptographyService) {
        this.cryptographyService = cryptographyService;
    }

    @Pointcut(value = "@annotation(com.synergism.blog.security.cryptography.note.CheckPassword)")
    public void CheckPassword() {
    }

    /**
     * 获取参数列表
     * 将参数名为password的参数值进行加密
     *
     * @param point 数据
     * @return 参数
     * @throws Throwable 函数运行抛出的异常
     */
    @Around(value = "CheckPassword()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
            Object[] args = point.getArgs();
            for (Object arg : args) {
                Class<?> argsClass = arg.getClass();
                Field[] fields = argsClass.getDeclaredFields();
                for (Field field : fields) {
                    if ("password".equals(field.getName())) {
                        field.setAccessible(true);
                        String value = (String) field.get(arg);
                        field.set(arg, cryptographyService.MySQLEncrypt(value));
                        break;
                    }
                }
            }

            return point.proceed(args);
    }
}
