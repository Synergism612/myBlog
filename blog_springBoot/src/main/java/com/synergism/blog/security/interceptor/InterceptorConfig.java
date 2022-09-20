package com.synergism.blog.security.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置类
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private final GlobalInterceptor globalInterceptor;

    /**
     * 构造函数
     * 自动注入
     * @param globalInterceptor 全局拦截器
     */
    @Autowired
    InterceptorConfig(GlobalInterceptor globalInterceptor){
        this.globalInterceptor = globalInterceptor;
    }

    /**
     * 注册拦截器到系统
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor);
    }
}
