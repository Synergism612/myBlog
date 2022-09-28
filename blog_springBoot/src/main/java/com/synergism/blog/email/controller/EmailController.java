package com.synergism.blog.email.controller;

import com.synergism.blog.blog.user.service.UserService;
import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.EmailService;
import com.synergism.blog.email.utils.CodeUtil;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TimeUtil;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    EmailService service;
    UserService userService;

    /**
     * 构造函数
     * 自动注入服务类
     *
     * @param emailService 邮箱服务
     * @param userService 用户服务
     */
    @Autowired
    EmailController(EmailService emailService,UserService userService) {
        this.service = emailService;
        this.userService = userService;
    }

    /**
     * 邮箱验证码接口
     *
     * @param mail 邮箱
     * @return 结果[null]
     * @throws MessagingException 邮箱发送失败异常
     */
    @GetMapping("/code")
    public Result<String> getMailCode(@RequestParam  String mail) throws MessagingException {
        try {
            //判断用户是否已存在
            if (userService.ifExist(mail)) {
                return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
            }
            //得到验证码邮件信息
            CodeMail codeMail = service.getCodeMail(mail);
            //判空
            if (TypeUtil.ifNull(codeMail)) {
                //为空则创建并给与邮箱
                codeMail = new CodeMail();
                codeMail.setMail(mail);
            } else {
                //不为空则判断时长,没超过60秒则抛出异常
                if (!TimeUtil.ifTimeOut(TimeUtil.toDate(codeMail.getTime()),new Date(),60)) {
                    //记录的时间距离现在不到一分钟，返回频繁操作
                    return Result.error(CodeMsg.MAIL_ERROR.fillArgs("频繁操作"));
                }
            }
            //不管空不空，都会更新数据进去
            String code = CodeUtil.code();
            codeMail.setCode(code);
            codeMail.setTime(TimeUtil.now());
            //调用发邮件
            service.sendCodeMail(mail,codeMail);
            return Result.success();
        } catch (MailException e) {
            throw new MailErrorException("发送失败");
        } catch (ParseException e){
            throw new MailErrorException("时间转换失败");
        }
    }
}
