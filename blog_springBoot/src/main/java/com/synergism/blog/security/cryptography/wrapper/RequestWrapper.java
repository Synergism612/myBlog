package com.synergism.blog.security.cryptography.wrapper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.synergism.blog.security.utils.AESUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Vector;

/**
 * 自定义请求
 */

public class RequestWrapper extends HttpServletRequestWrapper {
    // 暂存请求体数据
    private final String body;
    // 暂存参数体数据
    private final Map<String, Object> params;

    /**
     * 从原请求中获取数据
     *
     * @param request 原请求
     * @param key     密钥
     */
    @SuppressWarnings("unchecked") //忽略json转map警告提示
    public RequestWrapper(HttpServletRequest request, String key) {
        //super HttpServletRequestWrapper 构造方法构造请求
        super(request);
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
                int bytesRead;
                //开启循环
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    //按位读取
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ignored) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //解密操作，赋值请求体
        this.body = AESUtil.decrypt(stringBuilder.toString(), key);
        //解密获取参数体
        this.params = (Map<String, Object>) JSONObject.parseObject(AESUtil.decrypt(request.getParameter("params"), key),Map.class);
    }

    /**
     * 重写获取流数据方法，注意：
     * 一个http请求只能被调用一次流数据
     * 是否调用过用this.getReader()!=-1判断
     *
     * @return 流数据
     */
    @Override
    public ServletInputStream getInputStream() {
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
            public int read() {
                return byteArrayInputStream.read();
            }
        };

    }

    /**
     * 用于判断是否已经调用过流数据
     *
     * @return BufferedReader
     */
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 重写获取所有参数名方法
     *
     * @return 参数体名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(params.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        String[] results;
        Object o = params.get(name);

        if (o instanceof String){
            return (String) o;
        }
        if (o instanceof JSONArray){
            results = (String[]) ((JSONArray) o).toArray();
            return results[0];
        }
        return null;
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] results  = new String[1];
        Object o = params.get(name);

        if (o instanceof String){
           results[0] = (String) o;
        }
        if (o instanceof JSONArray){
            results = (String[]) ((JSONArray) o).toArray();
        }
        return results;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String,String[]> results = new HashMap<>();
        params.forEach((key,object)->{
            if (object instanceof String){
                results.put(key,new String[]{(String) object});
            }
            if (object instanceof JSONArray){
                results.put(key,(String[]) ((JSONArray) object).toArray());
            }
        });
        return results;
    }
}
