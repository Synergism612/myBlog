package com.synergism.blog.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.blog.user.entity.User;
import com.synergism.blog.email.entity.CodeMail;

import javax.mail.MessagingException;
import java.util.Map;

public interface EmailService extends IService<User> {

    /**
     * 获取验证码邮件内容
     * @param mail 邮箱(账号)
     * @return 验证码邮件内容
     */
    CodeMail getCodeMail(String mail);

    /**
     * 验证验证码
     * @param mail 邮箱(账号)
     * @param code 传入的验证码
     * @return 正确为真，错误为假
     */
    boolean verifyCode(String mail,String code);

    /**
     * 发送验证码邮件
     * @param to 发送对象
     * @param codeMail 验证码邮件内容
     * @throws MessagingException 发送失败
     */
    void sendCodeMail(String to, CodeMail codeMail) throws MessagingException;

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
