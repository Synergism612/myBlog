package com.synergism.blog.security.authentication.interceptor;

import com.synergism.blog.redis.RedisService;
import com.synergism.blog.security.authentication.entity.Auth;
import com.synergism.blog.security.enums.KeyEnum;
import com.synergism.blog.security.enums.RSAEnum;
import com.synergism.blog.security.utils.RSAUtil;
import com.synergism.blog.security.utils.SnowflakeIdWorker;
import com.synergism.blog.security.utils.URLUtil;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.utils.StringUtil.asString;
import static com.synergism.blog.utils.StringUtil.checkStringIfEmpty;

/**
 * 鉴权拦截器
 */
@Component
public class NewInterceptor implements HandlerInterceptor {

    private RedisService redis;

    @Autowired
    public NewInterceptor(RedisService redis) {
        this.redis = redis;
    }

    /**
     * 当某个 url 已经匹配到对应的 Controller 中的某个方法，且在这个方法执行之前 去执行。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  执行的函数
     * @return 返回 true 则放行，返回 false 则不会向后执行。
     * @throws Exception 抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String method = request.getMethod();
        String sessionID = request.getSession().getId();
        String ANOTHER_WORLD_KEY = request.getHeader(asString(KeyEnum.ANOTHER_WORLD_KEY));
        String AUTH_ID = request.getHeader(asString(KeyEnum.AUTH_ID));

        if (URLUtil.checkURLIfToError(url) || method.equals("OPTIONS")) return true;

        if (checkStringIfEmpty(ANOTHER_WORLD_KEY)) {
            if (URLUtil.checkURLIfToPublic(url)) {
                //创建id
                String auth_id = asString(new SnowflakeIdWorker(0, 0).nextId());
                //创建基本权限
                Auth auth = Auth.BASIC(sessionID);
                //写入redis
                redis.setValue(auth_id, auth);
                response.addHeader("AUTH_ID", auth_id);
                return true;
            }
        }

        if (!checkStringIfEmpty(AUTH_ID)){
            Auth auth = (Auth) redis.getValue(AUTH_ID);
            if (checkStringIfEmpty(auth.getUserKey()))
                auth.setUserKey(RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY, System.getProperty(asString(RSAEnum.PRIVATE_KEY))));
            redis.getAndSetValue(AUTH_ID,auth);
            URLUtil.checkURLIsPower(url, auth.getPower());
            response.addHeader("AUTH_ID", AUTH_ID);
            return true;
        }
        return false;
    }

    /**
     * 当某个 url 已经匹配到对应的 Controller 中的某个方法，且在执行完了该方法，但是在 DispatcherServlet视图渲染之前。
     * 所以在这个方法中有个 ModelAndView 参数，可以在此做一些修改动作。
     *
     * @param request      请求
     * @param response     响应
     * @param handler      执行的函数
     * @param modelAndView 视图
     * @throws Exception 抛出异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作。
     * 这个方法只有在 preHandle(……) 被成功执行后并且返回 true才会被执行。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  执行的函数
     * @param ex       错误
     * @throws Exception 抛出异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
