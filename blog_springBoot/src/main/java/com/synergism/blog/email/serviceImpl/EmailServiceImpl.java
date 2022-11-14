package com.synergism.blog.email.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.EmailService;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.mapper.UserMapper;
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

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailServiceImpl extends ServiceImpl<UserMapper, User> implements EmailService {

    private final String sender = "synergism2022@163.com";

    private final String template = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>激活邮件</title>\n" +
            "    <style type=\"text/css\">\n" +
            "        * {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            box-sizing: border-box;\n" +
            "            font-family: Arial, Helvetica, sans-serif;\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            background-color: #ECECEC;\n" +
            "        }\n" +
            "\n" +
            "        .container {\n" +
            "            width: 800px;\n" +
            "            margin: 50px auto;\n" +
            "        }\n" +
            "\n" +
            "        .header {\n" +
            "            height: 80px;\n" +
            "            background-color: #49bcff;\n" +
            "            border-top-left-radius: 5px;\n" +
            "            border-top-right-radius: 5px;\n" +
            "            padding-left: 30px;\n" +
            "        }\n" +
            "\n" +
            "        .header h2 {\n" +
            "            padding-top: 25px;\n" +
            "            color: white;\n" +
            "        }\n" +
            "\n" +
            "        .content {\n" +
            "            background-color: #fff;\n" +
            "            padding-left: 30px;\n" +
            "            padding-bottom: 30px;\n" +
            "            border-bottom: 1px solid #ccc;\n" +
            "        }\n" +
            "\n" +
            "        .content h2 {\n" +
            "            padding-top: 20px;\n" +
            "            padding-bottom: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .content p {\n" +
            "            padding-top: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            background-color: #fff;\n" +
            "            border-bottom-left-radius: 5px;\n" +
            "            border-bottom-right-radius: 5px;\n" +
            "            padding: 35px;\n" +
            "        }\n" +
            "\n" +
            "        .footer p {\n" +
            "            color: #747474;\n" +
            "            padding-top: 10px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div class=\"container\">\n" +
            "    <div class=\"header\">\n" +
            "        <h2>欢迎加入Synergism~</h2>\n" +
            "    </div>\n" +
            "    <div class=\"content\">\n" +
            "        <h2>亲爱的用户您好</h2>\n" +
            "        <p>您的邮箱：<b>%s</b></p>\n" +
            "        <p>您的验证码：<b>%s</b></p>\n" +
            "        <p>您注册时的日期：<b>%s</b></p>\n" +
            "        <p>当您在使用本网站时，务必要遵守法律法规</p>\n" +
            "        <p>如果您有什么疑问可以联系管理员，Email: <b>synergism2022@163.com</b></p>\n" +
            "    </div>\n" +
            "    <div class=\"footer\">\n" +
            "        <p>此为系统邮件，请勿回复</p>\n" +
            "        <p>请保管好您的信息，避免被他人盗用</p>\n" +
            "        <p>©Synergism</p>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";

    @Resource
    private JavaMailSender javaMailSender;

    private final CacheRedisService cacheRedisService;

    @Autowired
    EmailServiceImpl(CacheRedisService cacheRedisService) {
        this.cacheRedisService = cacheRedisService;
    }

    @Override
    public String getMailCode(String mail, String key) {

        //校验密钥并取出数据
        CodeMail codeMail = this.checkKeyValidity(key);

        //若取出的数据为空或者已经过了一分钟，重新创建对象
        if (TypeUtil.isNull(codeMail) || this.checkCodeTime(codeMail))
            codeMail = new CodeMail(mail);

        //若取出的数据与传入的账号不匹配，抛出异常
        if (!codeMail.getMail().equals(mail)) {
            throw new MailErrorException("用户名与密钥不匹配");
        }

        return this.sendCodeMail(mail, codeMail);
    }

    @Override
    public boolean checkCodeTime(CodeMail codeMail) {
        return TimeUtil.isTimeOut(TimeUtil.toDate(codeMail.getTime()), new Date(), 60);
    }

    @Override
    public CodeMail checkKeyValidity(String key) {
        if (StringUtil.isEmpty(key))
            return null;
        CodeMail result = this.getCodeMail(key);
        if (TypeUtil.isNull(result))
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
        if (TypeUtil.isNull(codeMail))
            return false;
        return codeMail.getCode().equals(code) && codeMail.getMail().equals(mail);
    }

    @Override
    public String sendCodeMail(String to, CodeMail codeMail) {
        try {
            String HTML = String.format(template, codeMail.getMail(),codeMail.getCode(),codeMail.getTime());
            this.sendHtmlMail(to,"验证码",HTML);
            return cacheRedisService.put(codeMail, TimeUtil.minutes(1));
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
}
