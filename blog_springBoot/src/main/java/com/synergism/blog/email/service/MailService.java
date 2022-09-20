package com.synergism.blog.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.blog.user.entity.User;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService extends IService<User> {

    /**
     * 发送文本邮件
     * @param to 发送对象
     * @param subject 主题
     * @param content 内容
     * */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 发送对象
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException 邮箱错误
     */
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;

    /**
     * 发送模板邮件
     *
     * @param to            发送对象
     * @param subject       主题
     * @param emailTemplate 模板
     * @param dataMap       数据
     * @throws MessagingException 邮件错误
     */
    void sendTemplateMail(String to, String subject, String emailTemplate, Map<String, String> dataMap) throws MessagingException;
}
