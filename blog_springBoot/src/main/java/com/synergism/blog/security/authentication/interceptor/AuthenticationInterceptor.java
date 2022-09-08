package com.synergism.blog.security.authentication.interceptor;

import com.synergism.blog.redis.RedisService;
import com.synergism.blog.security.authentication.entity.Auth;
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

/**
 * 鉴权拦截器
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private RedisService redis;

    @Autowired
    public AuthenticationInterceptor(RedisService redis) {
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
        //放行
        if(URLUtil.checkURLIfToPublic(request.getRequestURI())) return true;
        //跳过的请求
        if(request.getMethod().equals("OPTIONS"))return true;
        if(URLUtil.checkURLIfToError(request.getRequestURI())) return true;
        //获取AuthID
        String Auth_ID = request.getHeader("AUTH_ID");
        if (StringUtil.checkStringIfEmpty(Auth_ID)) { //若为空
            //创建id
            String auth_id = asString(new SnowflakeIdWorker(0, 0).nextId());
            //创建基本权限
            Auth auth = Auth.getInstance(request);
            //写入redis
            redis.setValue(auth_id, auth);
            response.addHeader("Auth_ID",auth_id);
        }else { //若存在
            //从redis中获取对应权限
            Auth auth = (Auth) redis.getValue(Auth_ID);
        }
        return true;
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
