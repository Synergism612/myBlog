package com.synergism.blog.security.cryptography.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.synergism.blog.exception.custom.KeyFailureException;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.cryptography.wrapper.RequestWrapper;
import com.synergism.blog.security.cryptography.wrapper.ResponseWrapper;
import com.synergism.blog.security.cryptography.utils.AESUtil;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.security.utils.URLUtil;
import com.synergism.blog.utils.StringUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.utils.StringUtil.asString;

/**
 * http过滤器
 * 用于将请求、响应替换为自定义请求、自定义响应
 */
@Component
public class HttpFilter implements Filter {

    private final CryptographyService cryptographyService;
    private final KeyManagementService keyManagementService;

    @Autowired
    HttpFilter(CryptographyService cryptographyService,KeyManagementService keyManagementService){
        this.cryptographyService = cryptographyService;
        this.keyManagementService = keyManagementService;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * 原为
     * filterChain.doFilter(servletRequest,servletResponse);
     * 替换请求为自定义请求
     *
     * @param servletRequest 请求
     * @param servletResponse 响应
     * @param filterChain 过滤器
     */
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //获取HttpServletRequest请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获得加密的密钥
        String ANOTHER_WORLD_KEY = keyManagementService.getAnotherWorldKey(httpServletRequest);
        //判断不使用自定义类的情况
        try {
            //判断是否需要直接通过或者重定向
            if (URLUtil.checkURLIfToPublic(httpServletRequest.getRequestURI()) || //获取公钥请求
                    StringUtil.checkStringIfEmpty(ANOTHER_WORLD_KEY) //公钥为空请求
            ) {
                if (URLUtil.checkURLIfToPublic(httpServletRequest.getRequestURI()))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    throw new KeyFailureException("重定向");
            } else {
                //解密得到密钥
                String key = cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY);
                //构建自定义请求与响应
                RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest, key ,cryptographyService);
                ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
                //传递到下一步
                filterChain.doFilter(requestWrapper, responseWrapper);
                //请求加密在自定义请求RequestWrapper中

                //响应加密
                JSONObject resultJson = new JSONObject(); //创建json对象
                Object data = JSON.parseObject(responseWrapper.getTextContent()); //读取结果字符串
                resultJson.put(KeyManagementService.ANOTHER_WORLD_RESPONSE(), data); //写入json
                String result = AESUtil.encrypt(resultJson.toJSONString(), key); //转字符串并加密

                //json数据流返回响应
                servletResponse.setContentLength(result.length()); //写入头部
                servletResponse.setContentType("application/json;charset=utf-8"); //写入头部
                ((HttpServletResponse) servletResponse).setStatus(200); //写入头部
                servletResponse.getOutputStream().write(result.getBytes()); //开启数据流
            }
        } catch (KeyFailureException e) {
            //出现密钥解密失败问题时重定向到错误接口
            ((HttpServletResponse) servletResponse).sendRedirect("/api/public/key/error");
        }
    }

    @Override
    public void destroy() {

    }
}
