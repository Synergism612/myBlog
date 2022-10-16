package com.synergism.blog.api.email.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.api.email.entity.CodeMail;
import com.synergism.blog.api.email.service.EmailService;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.mapper.UserMapper;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.security.cacheManager.service.CacheRedisService;
import com.synergism.blog.utils.StringUtil;
import com.synergism.blog.utils.TimeUtil;
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
import java.util.Date;
import java.util.Map;

@Service
public class EmailServiceImpl extends ServiceImpl<UserMapper, User> implements EmailService {

    private final String sender = "synergism2022@163.com";

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    private final CacheRedisService cacheRedisService;
    private final UserService userService;

    @Autowired
    EmailServiceImpl(CacheRedisService cacheRedisService, UserService userService) {
        this.cacheRedisService = cacheRedisService;
        this.userService = userService;
    }

    @Override
    public Result<String> getRegisterMailCode(String mail, String key) {
        if (userService.ifExist(mail)) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
        } else
            return Result.success(getMailCode(mail, key));
    }

    @Override
    public String getMailCode(String mail, String key) {

        //校验密钥并取出数据
        CodeMail codeMail = this.checkKeyValidity(key);

        //若取出的数据为空或者已经过了一分钟，重新创建对象
        if (TypeUtil.ifNull(codeMail) || this.checkCodeTime(codeMail))
            codeMail = new CodeMail(mail);

        //若取出的数据与传入的账号不匹配，抛出异常
        if (!codeMail.getMail().equals(mail)) {
            throw new MailErrorException("用户名与密钥不匹配");
        }

        return this.sendCodeMail(mail, codeMail);
    }

    @Override
    public boolean checkCodeTime(CodeMail codeMail) {
        return TimeUtil.ifTimeOut(TimeUtil.toDate(codeMail.getTime()), new Date(), 60);
    }

    @Override
    public CodeMail checkKeyValidity(String key) {
        if (StringUtil.checkStringIfEmpty(key))
            return null;
        CodeMail result = this.getCodeMail(key);
        if (TypeUtil.ifNull(result))
            return null;
        return result;
    }


    @Override
    public CodeMail getCodeMail(String key) {
        return (CodeMail) cacheRedisService.get(key);
    }

    @Override
    public boolean verifyCode(String key, String mail, String code) {
        CodeMail codeMail = this.getCodeMail(key);
        if (TypeUtil.ifNull(codeMail))
            return false;
        return codeMail.getCode().equals(code) && codeMail.getMail().equals(mail);
    }

    @Override
    public String sendCodeMail(String to, CodeMail codeMail) {
        try {
            this.sendTemplateMail(to, "验证码", "registerTemplate", codeMail.toMap());
            return cacheRedisService.put(codeMail, TimeUtil.Minutes(1));
        } catch (MessagingException e) {
            throw new MailErrorException("发送失败");
        }
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

    public void sendTemplateMail(String to, String subject, String emailTemplate, Map<String, String> dataMap) throws MessagingException {
        try {
            Context context = new Context();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }
            String templateContent = templateEngine.process(emailTemplate, context);
            //调用发送方法
            sendHtmlMail(to, subject, templateContent);
        } catch (MailException e) {
            throw new MailErrorException("发送失败");
        }
    }
}
