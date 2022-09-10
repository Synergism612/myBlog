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
    private String IP;
    private String HOST;
    private boolean AGENCY;

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
        Boolean AGENCY = true;
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
