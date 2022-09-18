package com.synergism.blog.email.controller;

import com.synergism.blog.email.entity.CodeMail;
import com.synergism.blog.email.service.MailService;
import com.synergism.blog.email.utils.CodeUtil;
import com.synergism.blog.redis.service.RedisService;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public Result<String> login(@RequestBody Map<String, String> mailMap) throws MessagingException, ParseException {
        String mail = mailMap.get("mail");
        Map map = (Map<String, String>) redis.getValue(mail);
        if (map != null) {
            CodeMail codeMail = CodeMail.getInstance(map);
            if ((int) (new Date().getTime() - TimeUtil.toDate(codeMail.getTime()).getTime()) / 6000 > 1) {
                //若redis中存在且记录的时间距离现在不到一分钟，返回请求过快
                return Result.error(CodeMsg.ACCESS_LIMIT_REACHED);
            }
        }


        String code = CodeUtil.code();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("email", mail);
        dataMap.put("code", code);
        dataMap.put("createTime", TimeUtil.now());
        service.sendTemplateMail(mail, "验证码", "registerTemplate", dataMap);
        redis.setEmail(mail, dataMap);
        return Result.success();
    }
}
