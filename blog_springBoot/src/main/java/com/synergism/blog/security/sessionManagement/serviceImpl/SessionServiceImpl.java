package com.synergism.blog.security.sessionManagement.serviceImpl;

import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import com.synergism.blog.security.entity.Power;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.security.sessionManagement.entity.Session;
import com.synergism.blog.security.sessionManagement.service.SessionService;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.synergism.blog.utils.StringUtil.isEmpty;
import static com.synergism.blog.utils.StringUtil.ifEmpty;
import static com.synergism.blog.utils.TimeUtil.weeks;

@Service
public class SessionServiceImpl implements SessionService {

    private final CacheRedisService cacheRedisService;
    private final CryptographyService cryptographyService;

    @Autowired
    SessionServiceImpl(CacheRedisService cacheRedisService, CryptographyService cryptographyService) {
        this.cacheRedisService = cacheRedisService;
        this.cryptographyService = cryptographyService;
    }

    /**
     * 通过会话id产生新的会话
     *
     * @param sessionID 前端请求的会话id
     * @param response  响应
     * @return 会话
     */
    public Session newSession(String sessionID, HttpServletResponse response) {
        //创建基本权限会话
        Session session = new Session(sessionID, "", "", Power.NOT_LOG_IN.getPower());
        //写入redis
        String EVIL_EYE = cacheRedisService.put(session, weeks(1));
        //写入响应头部
        response.setHeader("EVIL_EYE", EVIL_EYE);

        return session;
    }

    /**
     * 通过请求体产生新的会话
     *
     * @param request  请求体
     * @param response 响应
     * @return 会话
     */
    public Session newSession(HttpServletRequest request, HttpServletResponse response) {
        //获取构造参数
        String sessionID = request.getRequestedSessionId();
        String ANOTHER_WORLD_KEY = request.getHeader(KeyManagementService.ANOTHER_WORLD_KEY());
        String userKey = "";
        if (!isEmpty(ANOTHER_WORLD_KEY))
            cryptographyService.RSADecrypt(ANOTHER_WORLD_KEY);

        //创建基本权限会话
        Session session = new Session(sessionID, userKey, "", Power.NOT_LOG_IN.getPower());
        //写入redis
        String EVIL_EYE = cacheRedisService.put(session, weeks(1));
        //写入响应头部
        response.setHeader("EVIL_EYE", EVIL_EYE);

        return session;
    }

    /**
     * 从请求中获取缓存密钥
     * @param request 请求
     * @return EVIL_EYE
     */
    @Override
    public String getEVIL_EYE(HttpServletRequest request) {
        return request.getHeader(KeyManagementService.EVIL_EYE());
    }

    /**
     * 根据EVIL_EYE从redis中获取会话
     *
     * @param EVIL_EYE 主键
     * @return 会话
     */
    public Session getSession(String EVIL_EYE) {
        StringUtil.ifEmpty(EVIL_EYE, "邪王真眼");
        return (Session) cacheRedisService.get(EVIL_EYE);
    }

    /**
     * 根据请求从redis中获取会话
     *
     * @param request 请求
     * @return 会话
     */
    public Session getSession(HttpServletRequest request) {
        return this.getSession(this.getEVIL_EYE(request));
    }

    /**
     * 从用户信息更新会话数据
     *
     * @param request  请求
     * @param loginID  用户信息缓存对应主键
     * @param response 响应
     */
    public void updateSession(HttpServletRequest request, String loginID, HttpServletResponse response) {
        String EVIL_EYE = this.getEVIL_EYE(request);
        Session session = this.getSession(EVIL_EYE);
        //更新数据
        session.setLoginID(loginID);
        this.updateSession(EVIL_EYE, session, response);
    }

    /**
     * 更新会话数据
     *
     * @param EVIL_EYE 主键
     * @param session  会话
     * @param response 响应
     */
    public void updateSession(String EVIL_EYE, Session session, HttpServletResponse response) {
        cacheRedisService.update(EVIL_EYE, session, weeks(1));
        //写入响应头部
        response.setHeader("EVIL_EYE", EVIL_EYE);
    }

    @Override
    public boolean checkSessionExistLoginID(HttpServletRequest request) {
        Session session = this.getSession(request);
        return !StringUtil.isEmpty(session.getLoginID());
    }

    @Override
    public boolean removeLoginIDElement(HttpServletRequest request,String loginID,HttpServletResponse response) {
        String EVIL_EYE = this.getEVIL_EYE(request);
        Session session = this.getSession(EVIL_EYE);
        if (StringUtil.isEmpty(session.getLoginID())){
            return true;
        }
        if (loginID.equals(session.getLoginID())){
            cacheRedisService.remove(session.getLoginID());
            session.setLoginID("");
            this.updateSession(EVIL_EYE,session,response);
            return true;
        }else
            return false;
    }
}
