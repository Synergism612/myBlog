package com.synergism.blog.interceptor;

import com.synergism.blog.entity.Client;
import com.synergism.blog.enums.HeaderEnum;
import com.synergism.blog.enums.URLEnum;
import com.synergism.blog.exception.custom.IllegalRequestException;
import com.synergism.blog.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.util.StringUtil.checkStringContain;


@Component
public class GlobalInterceptor implements HandlerInterceptor {
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
        //获取客户端信息，ip地址和host名
        Client client = Client.getInstance(request);
        //获得cookies数据
        Cookie[] cookies = request.getCookies();
        //获取url路径
        String url = request.getRequestURI();

        //检查头部中是否存在异世界钥匙
        if(StringUtil.checkStringIfEmpty(request.getHeader(HeaderEnum.ANOTHER_WORLD_KEY()))) {
            //检查url是否指向获取公钥
            if(checkStringContain(url, URLEnum.PublicKeyPath())) return true;
            //若不存在且不是去获取，就抛出异常
            throw new IllegalRequestException("拒绝访问");
        }

        String sessionid = request.getSession().getId();

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
