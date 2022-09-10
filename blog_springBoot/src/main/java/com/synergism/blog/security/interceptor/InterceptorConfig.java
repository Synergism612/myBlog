package com.synergism.blog.global.security.authentication.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置类
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {

    //全局拦截器
    private GlobalInterceptor globalInterceptor;

    @Autowired
    InterceptorConfig(GlobalInterceptor newInterceptor){
        this.globalInterceptor = newInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor);
    }
}
