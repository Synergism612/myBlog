package com.synergism.blog.interceptor;

import com.synergism.blog.security.authentication.interceptor.AuthenticationInterceptor;
import com.synergism.blog.security.authentication.interceptor.NewInterceptor;
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
    //鉴权拦截器
    private AuthenticationInterceptor authenticationInterceptor;

    private NewInterceptor newInterceptor;

    @Autowired
    InterceptorConfig(GlobalInterceptor globalInterceptor,
                      AuthenticationInterceptor authenticationInterceptor,
                      NewInterceptor newInterceptor){
//        this.globalInterceptor = globalInterceptor;
//        this.authenticationInterceptor = authenticationInterceptor;
        this.newInterceptor = newInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(globalInterceptor);
//        registry.addInterceptor(authenticationInterceptor);
        registry.addInterceptor(newInterceptor);
    }
}
