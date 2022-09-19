package com.synergism.blog.email.controller;

import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.MailService;
import com.synergism.blog.email.utils.CodeUtil;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TimeUtil;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    MailService service;
    RedisService redis;

    @Autowired
    EmailController(MailService mailService, RedisService redis) {
        this.service = mailService;
        this.redis = redis;
    }

    @PostMapping("/code")
    public Result<String> getMailCode(@RequestBody Map<String, String> mailMap) throws MessagingException, ParseException {
        try {
            //获得对应邮箱
            String mail = mailMap.get("mail");
            //在redis查找
            CodeMail codeMail = (CodeMail) redis.getValue(mail);
            //判空
            if (TypeUtil.ifNull(codeMail)) {
                //为空则给与邮箱
                codeMail.setMail(mail);
            } else {
                //不为空则判断时长
                if ((int) (new Date().getTime() - TimeUtil.toDate(codeMail.getTime()).getTime()) / 6000 > 1) {
                    //记录的时间距离现在不到一分钟，返回频繁操作
                    return Result.error(CodeMsg.MAIL_ERROR.fillArgs("频繁操作"));
                }
            }
            //不管空不空，都会更新数据进去
            String code = CodeUtil.code();
            codeMail.setCode(code);
            codeMail.setTime(TimeUtil.now());
            //调用发邮件
            service.sendTemplateMail(mail, "验证码", "registerTemplate", codeMail.toMap());
            //更新redis中的数据
            redis.getAndSetValue(mail, codeMail);
            return Result.success();
        } catch (
                MailException e) {
            throw new MailErrorException("发送失败");
        }
    }
}
