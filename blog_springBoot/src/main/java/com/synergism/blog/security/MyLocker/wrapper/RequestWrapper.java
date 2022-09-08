package com.synergism.blog.security.myLocker.wrapper;

import com.synergism.blog.security.utils.AESUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * 自定义请求
 */

public class RequestWrapper extends HttpServletRequestWrapper  {
    /**
     * 暂存请求体数据
     */
    private final String body;
    /**
     * 暂存密钥
     */
    private final String key;

    /**
     * 从原请求中构造自定义请求
     * @param request 原请求
     */
    public RequestWrapper(HttpServletRequest request,String key) {
        //super HttpServletRequestWrapper 构造方法构造请求
        super(request);
        //获得密钥
        this.key = key;
        //定义后续使用变量
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        //开启异常处理
        try {
            //获得数据流
            inputStream = request.getInputStream();
            //判空
            if (inputStream != null) {
                //读取数据流的数据
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //定义字符数组
                char[] charBuffer = new char[128];
                //定义读取位置
                int bytesRead = -1;
                //开启循环
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    //按位读取
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                //否则返回“”
                stringBuilder.append("");
            }
        } catch (IOException ignored) {

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
        //解密操作，赋值请求体
        this.body = AESUtil.decrypt(stringBuilder.toString(),key);
    }

    /**
     * 重写获取流数据方法，注意：
     * 一个http请求只能被调用一次流数据
     * 是否调用过用this.getReader()!=-1判断
     * @return 流数据
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //使用缓存的解密后的请求体重新构造流数据
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
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
                return byteArrayInputStream.read();
            }
        };

    }

    /**
     * 用于判断是否已经调用过流数据
     * @return BufferedReader
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 获取缓存的解密后的请求体
     * @return 解密后请求体
     */
    public String getBody() {
        return this.body;
    }

}
