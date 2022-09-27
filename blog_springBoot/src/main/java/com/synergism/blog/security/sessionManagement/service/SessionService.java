package com.synergism.blog.security.sessionManagement.service;

import com.synergism.blog.blog.user.entity.UserInformation;
import com.synergism.blog.redis.service.RedisService;
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
    public Session newSession(String sessionID, HttpServletResponse response){
        //创建EVIL_EYE
        String EVIL_EYE = asString(new SnowflakeIdWorker(0, 0).nextId());
        //创建基本权限会话
        Session session = new Session(sessionID, "", "", Power.NOT_LOG_IN.getPower());
        //写入redis
        redis.setValue(EVIL_EYE, session);
        //写入响应头部
        response.addHeader("EVIL_EYE", EVIL_EYE);

        return session;
    }

    /**
     * 通过请求体产生新的会话
     * @param request 请求体
     * @param response 响应
     */
    public Session newSession(HttpServletRequest request, HttpServletResponse response){
        //获取构造参数
        String EVIL_EYE = asString(new SnowflakeIdWorker(0, 0).nextId());
        String sessionID = request.getRequestedSessionId();
        String ANOTHER_WORLD_KEY = request.getHeader(KeyManagementService.ANOTHER_WORLD_KEY());
        String userKey = "" ;
        if(!checkStringIfEmpty(ANOTHER_WORLD_KEY))
            cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY);

        //创建基本权限会话
        Session session = new Session(sessionID, userKey, "", Power.NOT_LOG_IN.getPower());
        //写入redis
        redis.setValue(EVIL_EYE, session);
        //写入响应头部
        response.addHeader("EVIL_EYE", EVIL_EYE);

        return  session;
    }

    /**
     * 根据EVIL_EYE从redis中获取会话
     * @param EVIL_EYE 主键
     * @return 会话
     */
    public Session getSession(String EVIL_EYE){
        checkStringIsEmpty(EVIL_EYE,"邪王真眼");
        return (Session)redis.getValue(EVIL_EYE);
    }

    /**
     * 从用户信息更新会话数据
     * @param request 请求
     * @param userInformation 用户
     * @param response 响应
     */
    public void updateSession(HttpServletRequest request, UserInformation userInformation, HttpServletResponse response){
        String EVIL_EYE = request.getHeader(KeyManagementService.EVIL_EYE());
        Session session = this.getSession(EVIL_EYE);
        //更新数据
        session.setUserName(userInformation.getName());
       this.updateSession(EVIL_EYE,session,response);
    }

    /**
     * 更新会话数据
     * @param EVIL_EYE 主键
     * @param session 会话
     * @param response 响应
     */
    public void updateSession(String EVIL_EYE ,Session session,HttpServletResponse response){
        redis.getAndSetValue(EVIL_EYE, session);
        //写入响应头部
        response.addHeader("EVIL_EYE", EVIL_EYE);
    }
}
