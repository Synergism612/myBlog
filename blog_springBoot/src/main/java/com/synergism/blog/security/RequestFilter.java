package com.synergism.blog.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求过滤器
 * 用于将请求替换为自定义请求
 */
@Component
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 原为
     * filterChain.doFilter(servletRequest,servletResponse);
     * 替换请求为自定义请求
     * @param servletRequest 请求
     * @param servletResponse 响应
     * @param filterChain 过滤器
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest),servletResponse);
    }

    @Override
    public void destroy() {

    }
}
