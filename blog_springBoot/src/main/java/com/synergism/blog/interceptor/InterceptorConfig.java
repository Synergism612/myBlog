package com.synergism.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {


    private GlobalInterceptor globalInterceptor;

    @Autowired
    InterceptorConfig(GlobalInterceptor globalInterceptor){
        this.globalInterceptor = globalInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor);
    }
}
