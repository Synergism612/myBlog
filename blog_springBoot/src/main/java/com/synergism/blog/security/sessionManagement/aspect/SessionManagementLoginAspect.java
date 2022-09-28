package com.synergism.blog.security.sessionManagement.aspect;

import com.synergism.blog.blog.user.entity.UserInformation;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.security.sessionManagement.service.SessionService;
import com.synergism.blog.utils.TypeUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class SessionManagementLoginAspect {

    SessionService sessionService;

    @Autowired
    SessionManagementLoginAspect(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Pointcut(value = "@annotation(com.synergism.blog.security.sessionManagement.note.SessionManagementLoginNote)")
    public void SessionManagementLogin(){}

    /**
     * 登录后绑定到会话
     * 时机为方法结束后
     * 条件是存在SessionManagementLoginNote注解
     * 获取方法返回值为result
     * @param result 方法的结果
     */
    @AfterReturning(value = "SessionManagementLogin()",
            returning = "result")
    public void afterReturning(Result<UserInformation> result){
        if (result.getCode()==200){
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            TypeUtil.isNull(servletRequestAttributes);
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            TypeUtil.isNull(response);
            assert response != null;
            sessionService.updateSession(request,result.getData(),response);
        }
    }
}
