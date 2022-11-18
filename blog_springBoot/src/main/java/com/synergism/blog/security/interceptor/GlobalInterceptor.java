package com.synergism.blog.security.interceptor;

import com.synergism.blog.exception.custom.KeyFailureException;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.security.sessionManagement.entity.Session;
import com.synergism.blog.security.sessionManagement.service.SessionService;
import com.synergism.blog.security.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;
    private final CryptographyService cryptographyService;

    @Autowired
    public GlobalInterceptor(SessionService sessionService, CryptographyService cryptographyService) {
        this.sessionService = sessionService;
        this.cryptographyService = cryptographyService;
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
        String ANOTHER_WORLD_KEY = request.getHeader(KeyManagementService.ANOTHER_WORLD_KEY());
        //获取权限ID
        String EVIL_EYE = request.getHeader(KeyManagementService.EVIL_EYE());

        //检查是否需要跳过
        if (URLUtil.checkURLIfToError(uri) || method.equals("OPTIONS")) {
            return true;
        }

        if (!ANOTHER_WORLD_KEY.isEmpty() && !EVIL_EYE.isEmpty()) {
            //获取权限
            Session session = sessionService.getSession(EVIL_EYE);
            if (!cryptographyService.filterVerify(ANOTHER_WORLD_KEY)) {
                sessionService.remove(session.getLoginKey());
                sessionService.remove(EVIL_EYE);
                throw new KeyFailureException("或许你可以刷新一下？");
            }
            if (session == null) {
                //为null则重新分配一个新的会话
                session = sessionService.newSession(request, response);
            }
            if (session.getUserKey().isEmpty())
                //用户密钥为空则写入用户密钥
                session.setUserKey(cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY));
            if (!session.getSessionID().equals(sessionID)) {
                //处理sessionID不同的情况，使用最新的sessionID
                session.setSessionID(sessionID);
            }
            //鉴权
//            URLUtil.checkURLIsPower(uri, session.getPower());
            //更新最新的会话信息
            sessionService.update(EVIL_EYE, session, response);
            return true;
        }

        //检查密钥是否为空
        if (ANOTHER_WORLD_KEY.isEmpty()
                && EVIL_EYE.isEmpty()
                && URLUtil.checkURLIfToPublic(uri)
        ) {
            //分配新的会话
            sessionService.newSession(sessionID, response);
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
