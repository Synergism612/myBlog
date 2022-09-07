package com.synergism.blog.security.MyLocker.Filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintWriter printWriter = new PrintWriter(outputStream);



    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }
        };
    }

    public void flush(){
        try {
            printWriter.flush();
            printWriter.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream getByteArrayOutputStream(){
        return outputStream;
    }


    public String getTextContent() {
        flush();
        return outputStream.toString();
    }
}
