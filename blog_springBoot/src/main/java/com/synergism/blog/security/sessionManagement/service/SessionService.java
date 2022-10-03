package com.synergism.blog.security.sessionManagement.service;

import com.synergism.blog.security.sessionManagement.entity.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface SessionService {

    /**
     * 通过会话id产生新的会话
     *
     * @param sessionID 前端请求的会话id
     * @param response  响应
     * @return 会话
     */
    Session newSession(String sessionID, HttpServletResponse response);
    /**
     * 通过请求体产生新的会话
     *
     * @param request  请求体
     * @param response 响应
     * @return 会话
     */
    Session newSession(HttpServletRequest request, HttpServletResponse response);


    /**
     * 从请求中获取缓存密钥
     * @param request 请求
     * @return EVIL_EYE
     */
    String getEVIL_EYE(HttpServletRequest request);

    /**
     * 根据EVIL_EYE从redis中获取会话
     *
     * @param EVIL_EYE 主键
     * @return 会话
     */
    Session getSession(String EVIL_EYE);

    /**
     * 根据请求从redis中获取会话
     *
     * @param request 请求
     * @return 会话
     */
    Session getSession(HttpServletRequest request);

    /**
     * 从用户信息更新会话数据
     *
     * @param request         请求
     * @param loginID  用户信息缓存对应主键
     * @param response        响应
     */
    void updateSession(HttpServletRequest request, String loginID, HttpServletResponse response);

    /**
     * 更新会话数据
     *
     * @param EVIL_EYE 主键
     * @param session  会话
     * @param response 响应
     */
    void updateSession(String EVIL_EYE, Session session, HttpServletResponse response);

    /**
     * 检查会话中是否存在登录信息ID
     * @param request 请求
     * @return 存在为真，反之为假
     */
    boolean checkSessionExistLoginID(HttpServletRequest request);

    /**
     * 通过会话中的登录信息ID删除对应信息
     * @param request 请求
     * @return 成功为真，反之为假
     */
    boolean removeLoginIDElement(HttpServletRequest request,String loginID, HttpServletResponse response );
}
