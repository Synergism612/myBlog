package com.synergism.blog.CORS.filter;

import com.synergism.blog.CORS.config.CORSConfig;
import org.springframework.beans.factory.annotation.Autowired;
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

    CORSConfig config;

    @Autowired
    CORSFilter(CORSConfig config){
        this.config = config;
    }

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.addHeader("Access-Control-Allow-Credentials", config.getAllow_Credentials());
        //允许该url访问，url返回
        response.addHeader("Access-Control-Allow-Origin",  request.getHeader("Origin"));
        //允许的请求方式，方式返回
        response.addHeader("Access-Control-Allow-Methods",  config.getAllow_Methods());
        //允许的头部，自定义头部返回
        response.addHeader("Access-Control-Allow-Headers",  config.getAllow_Headers());
        //允许访问的头部
        response.addHeader("Access-Control-Expose-Headers", config.getExpose_Headers());
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
