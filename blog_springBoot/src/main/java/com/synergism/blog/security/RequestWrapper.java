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
