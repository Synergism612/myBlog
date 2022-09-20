package com.synergism.blog.security.interceptor;

import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.security.entity.Auth;
import com.synergism.blog.security.enums.KeyEnum;
import com.synergism.blog.security.enums.RSAEnum;
import com.synergism.blog.security.utils.RSAUtil;
import com.synergism.blog.security.utils.SnowflakeIdWorker;
import com.synergism.blog.security.utils.URLUtil;
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
public class GlobalInterceptor implements HandlerInterceptor {

    private final RedisService redis;

    @Autowired
    public GlobalInterceptor(RedisService redis) {
        this.redis = redis;
    }

    /**
     * 当某个 url 已经匹配到对应的 Controller 中的某个方法，且在这个方法执行之前 去执行。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  执行的函数
     * @return 返回 true 则放行，返回 false 则不会向后执行。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取uri
        String uri = request.getRequestURI();
        //获取请求类型
        String method = request.getMethod();
        //获取sessionID
        String sessionID = request.getSession().getId();
        //获取密钥
        String ANOTHER_WORLD_KEY = request.getHeader(asString(KeyEnum.ANOTHER_WORLD_KEY));
        //获取权限ID
        String AUTH_ID = request.getHeader(asString(KeyEnum.AUTH_ID));

        //检查是否需要跳过
        if (URLUtil.checkURLIfToError(uri) || method.equals("OPTIONS")) return true;

        //检查密钥是否为空
        if (checkStringIfEmpty(ANOTHER_WORLD_KEY)) {
            if (URLUtil.checkURLIfToPublic(uri) && checkStringIfEmpty(AUTH_ID)) {
                //创建id
                String auth_id = asString(new SnowflakeIdWorker(0, 0).nextId());
                //创建基本权限
                Auth auth = Auth.BASIC(sessionID);
                //写入redis
                redis.setValue(auth_id, auth);
                //写入响应头部
                response.addHeader("AUTH_ID", auth_id);
                return true;
            }
        }

        //检查权限ID是否为空
        if (!checkStringIfEmpty(AUTH_ID)) {
            //获取权限
            Auth auth = (Auth) redis.getValue(AUTH_ID);
            if (auth == null) {
                //为null则重新分配一个新的权限
                AUTH_ID = asString(new SnowflakeIdWorker(0, 0).nextId());
                auth = Auth.getInstance(request);
            }
            if (checkStringIfEmpty(auth.getUserKey()))
                //用户密钥为空则写入用户密钥
                auth.setUserKey(RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY, System.getProperty(asString(RSAEnum.PRIVATE_KEY))));
            if (!auth.getSessionID().equals(sessionID)) {
                //处理sessionID不同的情况，使用最新的sessionID
                auth.setSessionID(sessionID);
            }
            //鉴权
            URLUtil.checkURLIsPower(uri, auth.getPower());
            //更新redis对应数据
            redis.getAndSetValue(AUTH_ID, auth);
            //更新写入响应头部
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
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作。
     * 这个方法只有在 preHandle(……) 被成功执行后并且返回 true才会被执行。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  执行的函数
     * @param ex       错误
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
