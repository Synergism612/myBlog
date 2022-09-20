package com.synergism.blog.CORS.filter;

import com.synergism.blog.security.enums.KeyEnum;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域处理过滤器
 */
@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
    }

    /**
     * 为响应添加CORS
     * @param servletRequest 请求
     * @param servletResponse 响应
     * @param filterChain 过滤器
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        //允许该url访问，url返回
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        //允许的请求方式，方式返回
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        //允许的头部，自定义头部返回
        res.addHeader("Access-Control-Allow-Headers", "content-type,Accept," + KeyEnum.ANOTHER_WORLD_KEY + "," + KeyEnum.AUTH_ID);
        //允许访问的头部
        res.addHeader("Access-Control-Expose-Headers",""+KeyEnum.AUTH_ID);
        //如果是预请求，直接返回
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            servletResponse.getWriter().println("ok");
            return;
        }
        //启动过滤
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
