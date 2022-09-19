package com.synergism.blog.email.controller;

import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.MailService;
import com.synergism.blog.email.utils.CodeUtil;
import com.synergism.blog.exception.custom.MailErrorException;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TimeUtil;
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

    //关闭强转类型编译提示
    @SuppressWarnings("unchecked")
    @PostMapping("/code")
    public Result<String> getMailCode(@RequestBody Map<String, String> mailMap) throws MessagingException, ParseException {
        try {
            String mail = mailMap.get("mail");
            Object object = redis.getValue(mail);
            Map<String, String> dataMap;
            //若redis中存在则判断时长，不存在则创建新map
            if (object != null) {
                dataMap = (Map<String, String>) object;
                CodeMail codeMail = CodeMail.getInstance(dataMap);
                if ((int) (new Date().getTime() - TimeUtil.toDate(codeMail.getTime()).getTime()) / 6000 > 1) {
                    //记录的时间距离现在不到一分钟，返回请求过快
                    return Result.error(CodeMsg.ACCESS_LIMIT_REACHED);
                }
            } else dataMap = new HashMap<>();

            String code = CodeUtil.code();
            dataMap.put("email", mail);
            dataMap.put("code", code);
            dataMap.put("createTime", TimeUtil.now());
            service.sendTemplateMail(mail, "验证码", "registerTemplate", dataMap);
            redis.getAndSetValue(mail, dataMap);
            return Result.success();
        } catch (
                MailException e) {
            throw new MailErrorException("发送失败");
        }
    }
}
