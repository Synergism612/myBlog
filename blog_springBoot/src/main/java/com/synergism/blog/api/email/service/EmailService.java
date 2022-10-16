package com.synergism.blog.api.email.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.api.email.entity.CodeMail;
import com.synergism.blog.result.entity.Result;

import javax.mail.MessagingException;
import java.util.Map;

public interface EmailService extends IService<User> {

    /**
     * 注册时使用的验证码接口
     * @param mail 邮箱(账号)
     * @param key 密钥
     * @return 结果[密钥]
     */
    Result<String> getRegisterMailCode(String mail, String key);

    /**
     * 获取一个新的邮箱验证码信息
     * @param mail 邮箱(账号)
     * @param key 密钥
     * @return 密钥
     */
    String getMailCode(String mail, String key);

    /**
     * 检查是否超时一分钟
     * @param codeMail 邮箱验证码信息
     * @return 超时为真，反之为假
     */
    boolean checkCodeTime(CodeMail codeMail);

    /**
     * 校验密钥有效性
     * 若为无效则返回null
     * @param key 密钥
     * @return 验证码邮件信息
     */
    CodeMail checkKeyValidity(String key);

    /**
     * 获取验证码邮件内容
     * @param key 缓存中对应的key
     * @return 验证码邮件内容
     */
    CodeMail getCodeMail(String key);

    /**
     * 验证验证码
     * @param key 缓存中对应的key
     * @param mail 邮箱(账号)
     * @param code 传入的验证码
     * @return 正确为真，错误为假
     */
    boolean verifyCode(String key,String mail,String code);

    /**
     * 发送验证码邮件
     * @param to 发送对象
     * @param codeMail 验证码邮件内容
     * @throws MessagingException 发送失败
     * @return 返回缓存对应key
     */
    String sendCodeMail(String to, CodeMail codeMail) throws MessagingException;

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
