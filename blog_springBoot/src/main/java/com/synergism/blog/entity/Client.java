package com.synergism.blog.entity;

import javax.servlet.http.HttpServletRequest;

public class Client {
    private String IP;
    private String HOST;
    private boolean AGENCY;

    private Client(String IP, String HOST, boolean AGENCY) {
        this.IP = IP;
        this.HOST = HOST;
        this.AGENCY = AGENCY;
    }

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
