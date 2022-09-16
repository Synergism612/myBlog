package com.synergism.blog.email.entity;

import com.synergism.blog.exception.custom.MailErrorException;
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
public class MailService {
    private String sender = "synergism2022@163.com";
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送文本邮件
     * @param to 发送对象
     * @param subject 主题
     * @param content 内容
     * */
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

    /**
     * 发送HTML邮件
     * @param to 发送对象
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException 邮箱错误
     */
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

    /**
     * 发送模板邮件
     *
     * @param to            发送对象
     * @param subject       主题
     * @param emailTemplate 模板
     * @param dataMap       数据
     * @throws MessagingException 邮件错误
     */
    public void sendTemplateMail(String to, String subject, String emailTemplate, Map<String, Object> dataMap) throws MessagingException {
        try {
            Context context = new Context();
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
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
