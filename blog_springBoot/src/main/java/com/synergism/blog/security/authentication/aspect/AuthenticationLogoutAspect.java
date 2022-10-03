package com.synergism.blog.security.authentication.aspect;

import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.security.sessionManagement.entity.Session;
import com.synergism.blog.security.sessionManagement.service.SessionService;
import com.synergism.blog.utils.StringUtil;
import com.synergism.blog.utils.TypeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

@Aspect
@Component
public class AuthenticationLogoutAspect {

    SessionService sessionService;

    @Autowired
    AuthenticationLogoutAspect(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Pointcut(value = "@annotation(com.synergism.blog.security.authentication.note.AuthenticationLogoutNote)")
    public void AuthenticationLogout() {
    }

    @Around(value = "AuthenticationLogout()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String loginID = "";
        Object[] args = point.getArgs();
        for (Object arg : args) {
            Class<?> argClass = arg.getClass();
            Field[] fields = argClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if ("loginID".equals(field.getName())) {
                    loginID = (String) field.get(arg);
                    break;
                }
            }
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        TypeUtil.isNull(servletRequestAttributes);
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        if (sessionService.removeLoginIDElement(request,loginID,response)){
            return point.proceed();
        }
        return Result.error(CodeMsg.USER_LOGOUT_ERROR);
    }
}
