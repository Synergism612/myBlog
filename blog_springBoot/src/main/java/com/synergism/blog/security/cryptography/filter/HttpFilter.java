package com.synergism.blog.security.cryptography.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.cryptography.wrapper.RequestWrapper;
import com.synergism.blog.security.cryptography.wrapper.ResponseWrapper;
import com.synergism.blog.security.cryptography.utils.AESUtil;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.utils.StringUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.synergism.blog.security.utils.URLUtil.checkURLIfToPublic;
import static com.synergism.blog.utils.StringUtil.isEmpty;

/**
 * http过滤器
 * 用于将请求、响应替换为自定义请求、自定义响应
 */
@Component
public class HttpFilter implements Filter {

    private final CryptographyService cryptographyService;
    private final KeyManagementService keyManagementService;

    @Autowired
    HttpFilter(CryptographyService cryptographyService, KeyManagementService keyManagementService) {
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
     * @param servletRequest  请求
     * @param servletResponse 响应
     * @param filterChain     过滤器
     */
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //获取HttpServletRequest请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //去向uri
        String uri = httpServletRequest.getRequestURI();
        //获得加密的密钥
        String ANOTHER_WORLD_KEY = keyManagementService.getAnotherWorldKey(httpServletRequest);

        switch (this.situationAnalysis(ANOTHER_WORLD_KEY, uri)) {
            case 0: //没有密钥，不前往公共接口
            case 3: //有密钥，不前往公共接口 -- 密钥不合法
                ((HttpServletResponse) servletResponse).sendRedirect("/api/public/key/error");
                break;
            case 1: //没有密钥，前往公共接口
            case 4: //有密钥，前往公共接口 -- 密钥合法
            case 5: //有密钥，前往公共接口 -- 密钥不合法
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            case 2: //有密钥，不前往公共接口 -- 密钥合法
                this.filterCore(ANOTHER_WORLD_KEY, httpServletRequest, servletResponse, filterChain);
                break;
        }
    }

    @Override
    public void destroy() {

    }

    public int situationAnalysis(String ANOTHER_WORLD_KEY, String uri) {
        //没有密钥，不前往公共接口
        if (isEmpty(ANOTHER_WORLD_KEY) && !checkURLIfToPublic(uri)) {
            return 0;
        }
        //没有密钥，前往公共接口
        if (isEmpty(ANOTHER_WORLD_KEY) && checkURLIfToPublic(uri)) {
            return 1;
        }
        //有密钥，不前往公共接口
        if (!isEmpty(ANOTHER_WORLD_KEY) && !checkURLIfToPublic(uri)) {
            //密钥合法
            if (cryptographyService.filterVerify(ANOTHER_WORLD_KEY))
                return 2;
                //密钥不合法
            else
                return 3;
        }
        //有密钥，前往公共接口
        if (!isEmpty(ANOTHER_WORLD_KEY) && checkURLIfToPublic(uri)) {
            //密钥合法
            if (cryptographyService.filterVerify(ANOTHER_WORLD_KEY))
                return 4;
                //密钥不合法
            else
                return 5;
        }
        return 0;
    }

    public void filterCore(String ANOTHER_WORLD_KEY, HttpServletRequest httpServletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String key = cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY);
        //构建自定义请求与响应
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest, key, cryptographyService);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        //传递到下一步
        filterChain.doFilter(requestWrapper, responseWrapper);
        //请求加密在自定义请求RequestWrapper中

        ObjectMapper objectMapper = new ObjectMapper();
        Result<?> resultClass;
        String textContent = responseWrapper.getTextContent();
        if (StringUtil.isEmpty(textContent)) {
            resultClass = Result.error(CodeMsg.SERVER_ERROR);
        } else {
            resultClass = objectMapper.readValue(textContent, Result.class);

        }
        String json = objectMapper.writeValueAsString(resultClass);
        String result = AESUtil.encrypt(json, key); //转字符串并加密

        //json数据流返回响应
        servletResponse.setContentLength(result.length()); //写入头部
        servletResponse.setContentType("application/json;charset=utf-8"); //写入头部
        ((HttpServletResponse) servletResponse).setStatus(200); //写入头部
        servletResponse.getOutputStream().write(result.getBytes()); //开启数据流
    }
}
