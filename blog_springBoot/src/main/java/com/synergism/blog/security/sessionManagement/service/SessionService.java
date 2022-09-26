package com.synergism.blog.security.sessionManagement.service;

import com.synergism.blog.blog.user.entity.User;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.security.keyManagement.enums.KeyEnum;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.entity.Power;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.security.sessionManagement.entity.Session;
import com.synergism.blog.security.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.utils.StringUtil.*;

@Service
public class SessionService {

    private final RedisService redis;
    private final CryptographyService cryptographyService;

    @Autowired
    SessionService(RedisService redis, CryptographyService cryptographyService){
        this.redis = redis;
        this.cryptographyService = cryptographyService;
    }

    /**
     * 通过会话id产生新的会话
     * @param sessionID 前端请求的会话id
     * @param response 响应
     */
    public void newSession(String sessionID, HttpServletResponse response){
        //创建AUTH_ID
        String AUTH_ID = asString(new SnowflakeIdWorker(0, 0).nextId());
        //创建基本权限会话
        Session session = new Session(sessionID, "", "", "", Power.NOT_LOG_IN.getPower());
        //写入redis
        redis.setValue(AUTH_ID, session);
        //写入响应头部
        response.addHeader("AUTH_ID", AUTH_ID);
    }

    /**
     * 通过请求体产生新的会话
     * @param request 请求体
     * @param response 响应
     */
    public void newSession(HttpServletRequest request, HttpServletResponse response){
        //获取构造参数
        String AUTH_ID = asString(new SnowflakeIdWorker(0, 0).nextId());
        String sessionID = request.getRequestedSessionId();
        String ANOTHER_WORLD_KEY = request.getHeader(KeyManagementService.ANOTHER_WORLD_KEY());
        String userKey = "" ;
        if(!checkStringIfEmpty(ANOTHER_WORLD_KEY))
            cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY);

        //创建基本权限会话
        Session session = new Session(sessionID, userKey, "", "", Power.NOT_LOG_IN.getPower());
        //写入redis
        redis.setValue(AUTH_ID, session);
        //写入响应头部
        response.addHeader("AUTH_ID", AUTH_ID);
    }

    /**
     * 根据AUTH_ID从redis中获取会话
     * @param AUTH_ID 主键
     * @return 会话
     */
    public Session getSession(String AUTH_ID){
        checkStringIsEmpty(AUTH_ID,"权限密钥");
        return (Session)redis.getValue(AUTH_ID);
    }

    /**
     * 更新对应会话数据
     * @param request 请求
     * @param user 用户
     * @param response 响应
     */
    public void updateSession(HttpServletRequest request,User user,HttpServletResponse response){
        String AUTH_ID = request.getHeader(KeyManagementService.AUTH_ID());
        Session session = this.getSession(AUTH_ID);
        //更新数据
        session.setUserName(user.getName());
        session.setPassword(user.getPassword());
        redis.getAndSetValue(AUTH_ID, session);
        //写入响应头部
        response.addHeader("AUTH_ID", AUTH_ID);
    }
}
