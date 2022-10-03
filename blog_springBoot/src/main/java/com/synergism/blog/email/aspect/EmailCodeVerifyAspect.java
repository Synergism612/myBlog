package com.synergism.blog.email.aspect;

import com.synergism.blog.email.service.EmailService;
import com.synergism.blog.exception.custom.RegisterFailException;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
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
public class EmailCodeVerifyAspect {

    EmailService emailService;

    @Autowired
    EmailCodeVerifyAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @Pointcut("@annotation(com.synergism.blog.email.note.EmailCodeVerifyNote)")
    public void EmailVerifyCode() {
    }

    /**
     * 获取参数列表
     * 获取参数中对应的账号、验证码、密钥
     * 进行验证码校验
     *
     * @param point 数据
     * @return 参数
     */
    @Around(value = "EmailVerifyCode()")
    public Object around(ProceedingJoinPoint point) {
        try {
            String username = "";
            String code = "";
            String key = "";
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
                    if ("key".equals(field.getName())) {
                        key = (String) field.get(arg);
                    }
                }
            }
            if (StringUtil.checkStringsIfEmpty(username, code))
                return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("用户名与验证码不能为空"));

            //校验验证码
            if (emailService.verifyCode(key, username, code))
                return point.proceed();

            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("验证码错误"));
        } catch (Throwable e) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("验证码校验异常"));
        }
    }
}
