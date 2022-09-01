package com.synergism.blog.interceptor;

import com.synergism.blog.entity.Client;
import com.synergism.blog.enums.CookieEnum;
import com.synergism.blog.enums.HeaderEnum;
import com.synergism.blog.enums.RSAEnum;
import com.synergism.blog.util.CheckUtil;
import com.synergism.blog.util.RSAUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

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

        String a = request.getHeader(HeaderEnum.ANOTHER_WORLD_KEY());

//        if(CheckUtil.checkStringIfEmpty(a)) {
//            response.
//        }

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
     * @throws Exception
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

    /**
     * 用于补全缺失的cookie信息
     * @param request
     * @param response
     * @param cookies
     * @throws NoSuchAlgorithmException
     */
    private void completionCookie(HttpServletRequest request, HttpServletResponse response, Cookie[] cookies) throws NoSuchAlgorithmException {
        if (cookies == null) {
            Map<String, String> keyPair = RSAUtil.getKeyPair();
            response.addCookie(new Cookie(CookieEnum.PUBLIC_KEY(), keyPair.get(RSAEnum.PUBLIC())));
            request.getSession(true);
        } else {
            for (Cookie cookie : cookies) {
                if (CookieEnum.PUBLIC_KEY().equals(cookie.getName())) {
                    if (!CheckUtil.checkStringIfEmpty(cookie.getValue())){
                    }
                    else {
                        Map<String, String> keyPair = RSAUtil.getKeyPair();
                        response.addCookie(new Cookie(CookieEnum.PUBLIC_KEY(), keyPair.get(RSAEnum.PUBLIC())));
                    }
                }
                if (CookieEnum.JSESSIONID().equals(cookie.getName())) {
                    if (CheckUtil.checkStringIfEmpty(cookie.getValue())){
                        request.getSession(true);
                    }
                }
            }
        }
    }
}
