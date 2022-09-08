package com.synergism.blog.security.myLocker.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.synergism.blog.security.enums.KeyEnum;
import com.synergism.blog.security.enums.RSAEnum;
import com.synergism.blog.security.myLocker.wrapper.RequestWrapper;
import com.synergism.blog.security.myLocker.wrapper.ResponseWrapper;
import com.synergism.blog.security.utils.AESUtil;
import com.synergism.blog.security.utils.RSAUtil;
import com.synergism.blog.utils.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.synergism.blog.utils.StringUtil.asString;

/**
 * http过滤器
 * 用于将请求、响应替换为自定义请求、自定义响应
 */
@Component
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 原为
     * filterChain.doFilter(servletRequest,servletResponse);
     * 替换请求为自定义请求
     *
     * @param servletRequest  请求
     * @param servletResponse 响应
     * @param filterChain     过滤器
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取HttpServletRequest请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获得加密的密钥
        String ANOTHER_WORLD_KEY = httpServletRequest.getHeader(StringUtil.asString(KeyEnum.ANOTHER_WORLD_KEY));
        //判断不使用自定义类的情况
        if (httpServletRequest.getRequestURL().toString().contains("/api/public/key") || //获取公钥请求
                StringUtil.checkStringIfEmpty(ANOTHER_WORLD_KEY)|| //公钥为空请求
                ANOTHER_WORLD_KEY.equals(System.getProperty(asString(KeyEnum.ANOTHER_WORLD_KEY))) //公钥不匹配请求
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //解密得到密钥
            String key = RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY, System.getProperty(asString(RSAEnum.PRIVATE_KEY)));
            //构建自定义请求与响应
            RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest, key);
            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
            //传递到下一步
            filterChain.doFilter(requestWrapper, responseWrapper);
            //请求加密在自定义请求RequestWrapper中

            //响应加密
            JSONObject resultJson = new JSONObject(); //创建json对象
            Object data = JSON.parseObject(responseWrapper.getTextContent()); //读取结果字符串
            resultJson.put(asString(KeyEnum.ANOTHER_WORLD_RESPONSE), data); //写入json
            String result = AESUtil.encrypt(resultJson.toJSONString(), key); //转字符串并加密

            //json数据流返回响应
            servletResponse.setContentLength(result.length()); //写入头部
            servletResponse.setContentType("application/json;charset=utf-8"); //写入头部
            ((HttpServletResponse) servletResponse).setStatus(200); //写入头部
            servletResponse.getOutputStream().write(result.getBytes()); //开启数据流
        }
    }

    @Override
    public void destroy() {

    }
}
