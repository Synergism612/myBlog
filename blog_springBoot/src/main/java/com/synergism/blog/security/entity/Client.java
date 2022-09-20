package com.synergism.blog.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户端实体类
 * 记录客户端信息
 */
@Getter
@Setter
public class Client {
    //ip
    private String IP;
    //host
    private String HOST;
    //是否为转发
    private boolean AGENCY;

    /**
     * 构造函数
     * @param IP ip
     * @param HOST host
     * @param AGENCY 是否为转发
     */
    private Client(String IP, String HOST, boolean AGENCY) {
        this.IP = IP;
        this.HOST = HOST;
        this.AGENCY = AGENCY;
    }

    /**
     * 用于从请求中提取ip和host名
     * @param request 请求
     * @return 封装好的客户端信息
     */
    public static Client getInstance(HttpServletRequest request) {
        String IP = request.getHeader("x - forwarded - for");
        boolean AGENCY = true;
        if (IP == null || IP.length() == 0 ||"unknown".equalsIgnoreCase(IP)){
            IP = request.getHeader("Proxy - Client - IP");
        }
        if (IP == null || IP.length() == 0 ||"unknown".equalsIgnoreCase(IP)){
            IP = request.getHeader("WL - Proxy - Client - IP");
        }
        if (IP == null || IP.length() == 0 ||"unknown".equalsIgnoreCase(IP)){
            IP = request.getRemoteAddr();
            AGENCY = false;
        }
       return new Client(IP,request.getRemoteHost(),AGENCY);
    }
}
