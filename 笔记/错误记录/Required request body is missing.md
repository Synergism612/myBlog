# Required request body is missing

请求体没有内容

## 问题再现

``blog_vue\src\axios\axios.ts``

    import { apiEnum } from "@/enum/apiEnum";
    import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
    import Result from "@/entity/Result";
    import { store } from "@/store";
    import AESUtil from "@/utils/AESUtil";
    import CryptoJS from "crypto-js";

    //请求根路径
    const baseURL = "http://localhost:8088";

    const publicKeyURL = "/api/public/key";
    class Axios {
      //axios配置
      config = {
        //配置请求根路径
        baseURL: baseURL,
        //设置超时时间
        timeout: apiEnum.TIMEOUT,
        //跨域设置
        withCredentials: true,
        //自定义头部
        headers: {
          ANOTHER_WORLD_KEY: " ",
        },
        // `transformRequest` 允许在向服务器发送前，修改请求数据
        transformRequest: [
          (data: string) => {
            // 对 data 进行加密
            const encJson = AESUtil.encrypt(JSON.stringify(data), store.state.KEY);
            return encJson;
          },
        ],
      };

      // 创建axios对象
      service: AxiosInstance;
      //构造函数
      constructor() {
        this.service = axios.create(this.config);
        //请求拦截
        this.service.interceptors.request.use((request: AxiosRequestConfig) => {
          console.log("请求拦截:" + request.url);
          if (request && request.headers) {
            if (request.url == publicKeyURL) return request;
            // 多一步判断
            request.headers["ANOTHER_WORLD_KEY"] = store.state.ANOTHER_WORLD_KEY;
          }
          return request;
        });

        //响应拦截
        this.service.interceptors.response.use((response: AxiosResponse) => {
          return Result.getResult(response);
        });
      }
    }

    export default new Axios().service;

## 问题来源

* 实现功能  
  为了实现前端全局加密，后端全局解密  
  我配置了一个过滤器RequestFilter类，将ServletRequest替换为我自定义的requestWrapper类
  ``blog_springBoot\src\main\java\com\synergism\blog\security\RequestFilter.java``  
  替换后续的请求体

      package com.synergism.blog.security;

      import org.springframework.stereotype.Component;

      import javax.servlet.*;
      import javax.servlet.http.HttpServletRequest;
      import java.io.IOException;

      @Component
      public class RequestFilter implements Filter {
          @Override
          public void init(FilterConfig filterConfig) throws ServletException {

          }

          @Override
          public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
              RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
              filterChain.doFilter(requestWrapper,servletResponse);
          }

          @Override
          public void destroy() {

          }
      }

  ``blog_springBoot\src\main\java\com\synergism\blog\security\RequestWrapper.java``  
  继承HttpServletRequestWrapper，解密请求体后创建数据流

      package com.synergism.blog.security;

      import com.synergism.blog.enums.HeaderEnum;
      import com.synergism.blog.enums.RSAEnum;
      import com.synergism.blog.util.AESUtil;
      import com.synergism.blog.util.RSAUtil;
      import com.synergism.blog.util.StringUtil;

      import javax.servlet.ReadListener;
      import javax.servlet.ServletInputStream;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletRequestWrapper;
      import java.io.*;

      import static com.synergism.blog.util.StringUtil.asString;


      public class RequestWrapper extends HttpServletRequestWrapper  {
          private final String body;
          private final String key;

          public RequestWrapper(HttpServletRequest request) {
              super(request);
              String ANOTHER_WORLD_KEY = request.getHeader(StringUtil.asString(HeaderEnum.ANOTHER_WORLD_KEY));
              this.key = RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY ,System.getProperty(asString(RSAEnum.PRIVATE_KEY)));
              StringBuilder stringBuilder = new StringBuilder();
              BufferedReader bufferedReader = null;
              InputStream inputStream = null;
              try {
                  inputStream = request.getInputStream();
                  if (inputStream != null) {
                      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                      char[] charBuffer = new char[128];
                      int bytesRead = -1;
                      while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                          stringBuilder.append(charBuffer, 0, bytesRead);
                      }
                  } else {
                      stringBuilder.append("");
                  }
              } catch (IOException ex) {

              } finally {
                  if (inputStream != null) {
                      try {
                          inputStream.close();
                      }
                      catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
                  if (bufferedReader != null) {
                      try {
                          bufferedReader.close();
                      }
                      catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              }
              this.body = AESUtil.decrypt(stringBuilder.toString(),key);
          }

          @Override
          public ServletInputStream getInputStream() throws IOException {
              final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
              ServletInputStream servletInputStream = new ServletInputStream() {
                  @Override
                  public boolean isFinished() {
                      return false;
                  }
                  @Override
                  public boolean isReady() {
                      return false;
                  }
                  @Override
                  public void setReadListener(ReadListener readListener) {
                  }
                  @Override
                  public int read() throws IOException {
                      int read = byteArrayInputStream.read();
                      return read;
                  }
              };
              return servletInputStream;

          }

          @Override
          public BufferedReader getReader() throws IOException {
              return new BufferedReader(new InputStreamReader(this.getInputStream()));
          }

          public String getBody() {
              return this.body;
          }

      }

* 报错信息

  ``2022-09-06 14:02:27.348  WARN 8544 --- [nio-8088-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: Required request body is missing: public com.synergism.blog.result.Result<java.lang.String> com.synergism.blog.controller.PublicController.test(java.lang.String)]
``

## 问题解决

我一直以为是后端问题，一直将后端改来改去，始终无法解决  
搜索中该报错是因为没有找到请求体，有两种解答  

1. 因为在过滤器或拦截器中对请求体操作，导致数据流不能再被读取  

        但是我已经通过RequestWrapper取代了请求，并且重写了数据流，不应该存在这个问题

2. 请求体的数据格式并不是json

        通过把前端的加密操作注释后，意外发现可以正常运行，控制层可以获得对应的参数，故而我们可以知道，是前端的数据格式出了问题，仔细观看F12的网络记录，发现没有加密时数据格式为Requst Payload而加密后数据格式为From Data，问题就在这里。

修改axios文件，在头部中添加数据格式的声明

    "Content-Type": "application/json; charset=utf-8",
    "Accept": "application/json",

修改后的的axios文件

    import { apiEnum } from "@/enum/apiEnum";
    import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
    import Result from "@/entity/Result";
    import { store } from "@/store";
    import AESUtil from "@/utils/AESUtil";
    import CryptoJS from "crypto-js";

    //请求根路径
    const baseURL = "http://localhost:8088";

    const publicKeyURL = "/api/public/key";
    class Axios {
      //axios配置
      config = {
        //配置请求根路径
        baseURL: baseURL,
        //设置超时时间
        timeout: apiEnum.TIMEOUT,
        //跨域设置
        withCredentials: true,
        //自定义头部
        headers: {
          ANOTHER_WORLD_KEY: " ",
          "Content-Type": "application/json; charset=utf-8",
          Accept: "application/json",
        },
        // `transformRequest` 允许在向服务器发送前，修改请求数据
        transformRequest: [
          (data: string) => {
            // 对 data 进行加密
            return AESUtil.encrypt(JSON.stringify(data), store.state.KEY);
          },
        ],
      };

      // 创建axios对象
      service: AxiosInstance;
      //构造函数
      constructor() {
        this.service = axios.create(this.config);
        //请求拦截
        this.service.interceptors.request.use((request: AxiosRequestConfig) => {
          console.log("请求拦截:" + request.url);
          if (request && request.headers) {
            if (request.url == publicKeyURL) return request;
            // 多一步判断
            request.headers["ANOTHER_WORLD_KEY"] = store.state.ANOTHER_WORLD_KEY;
          }
          return request;
        });

        //响应拦截
        this.service.interceptors.response.use((response: AxiosResponse) => {
          return Result.getResult(response);
        });
      }
    }

    export default new Axios().service;
