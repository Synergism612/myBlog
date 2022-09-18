package com.synergism.blog.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.blog.user.entity.User;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService extends IService<User> {
    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content) throws MessagingException;

    public void sendTemplateMail(String to, String subject, String emailTemplate, Map<String, String> dataMap) throws MessagingException;
}
