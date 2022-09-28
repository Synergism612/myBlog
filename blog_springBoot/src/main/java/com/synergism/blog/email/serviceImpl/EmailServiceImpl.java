package com.synergism.blog.email.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.blog.user.service.UserService;
import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.EmailService;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.blog.user.entity.User;
import com.synergism.blog.blog.user.mapper.UserMapper;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailServiceImpl extends ServiceImpl<UserMapper, User> implements EmailService {

    private final String sender = "synergism2022@163.com";

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    private final RedisService redisService;
    private final UserService userService;

    @Autowired
    EmailServiceImpl(RedisService redisService,UserService userService){
        this.redisService = redisService;
        this.userService = userService;
    }

    @Override
    public CodeMail getCodeMail(String mail) {
        return (CodeMail)redisService.getValue(mail);
    }

    @Override
    public boolean verifyCode(String mail, String code) {
        CodeMail codeMail =(CodeMail)redisService.getValue(mail);
        if (TypeUtil.ifNull(codeMail))
            return false;
        return codeMail.getCode().equals(code);
    }

    @Override
    public void sendCodeMail(String to, CodeMail codeMail) throws MessagingException {
        this.sendTemplateMail(to, "验证码",  "registerTemplate", codeMail.toMap());
        //更新redis
        redisService.setEmail(to,codeMail);
    }

    public void sendSimpleMail(String to, String subject, String content) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            simpleMailMessage.setFrom(sender);
            //发送邮件
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new MailErrorException("发送失败");
        }
    }

    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setFrom(sender);
            //发送邮件
            javaMailSender.send(mimeMailMessage);
        } catch (MailException e) {
            throw new MailErrorException("发送失败");
        }
    }

    public void sendTemplateMail(String to, String subject, String emailTemplate, Map<String,String> dataMap) throws MessagingException {
        try {
            Context context = new Context();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }
            String templateContent = templateEngine.process(emailTemplate, context);
            //调用发送方法
            sendHtmlMail(to,subject,templateContent);
        } catch (MailException e) {
            throw new MailErrorException("发送失败");
        }
    }
}
