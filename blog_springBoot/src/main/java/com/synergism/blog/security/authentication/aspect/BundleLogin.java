package com.synergism.blog.security.authentication.aspect;

import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import com.synergism.blog.security.sessionManagement.service.SessionService;
import com.synergism.blog.utils.TimeUtil;
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

@Aspect
@Component
public class BundleLogin {

    final SessionService sessionService;
    final CacheRedisService cacheRedisService;

    @Autowired
    BundleLogin(SessionService sessionService, CacheRedisService cacheRedisService) {
        this.sessionService = sessionService;
        this.cacheRedisService = cacheRedisService;
    }

    @Pointcut(value = "@annotation(com.synergism.blog.security.authentication.note.BundleLogin)")
    public void BundleLogin() {
    }

    /**
     * 登录后绑定到会话
     * 时机为方法结束后
     * 条件是存在SessionManagementLoginNote注解
     *
     * @param point 切面数据
     */
    @Around(value = "BundleLogin()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        TypeUtil.ifNull(servletRequestAttributes);
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        TypeUtil.ifNull(response);
        assert response != null;

        if (sessionService.checkSessionExistLoginID(request)) {
            return Result.error(CodeMsg.USER_IS_LOGIN);
        }

        Object object = point.proceed();
        String loginID;
        UserInformation userInformation;
        Result<?> result = null;
        if (object instanceof Result<?>) {
            result = (Result<?>) object;
        }
        TypeUtil.ifNull(result);
        assert result != null;
        if (result.getCode() == 200) {
            userInformation = (UserInformation) result.getData();
            loginID = cacheRedisService.put(userInformation, TimeUtil.weeks(1));
            sessionService.update(request, loginID, response);
            return Result.success(new Object[]{loginID, userInformation});
        }
        return result;
    }
}
