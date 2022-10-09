package com.synergism.blog.api.email.entity;

import com.synergism.blog.api.email.utils.CodeUtil;
import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱验证码实体类
 */
@Getter
@Setter
public class CodeMail {

    //目标邮箱
    private String mail;
    //验证码
    private String code;
    //时间
    private String time;

    /**
     * 从map中读取
     * @param map map
     * @return CodeMail
     */
    public static CodeMail getInstance(Map<String,String> map){
        return new CodeMail(map.get("mail"),map.get("code"),map.get("createTime"));
    }


    public CodeMail(String mail) {
        this.mail = mail;
        this.code = CodeUtil.code();
        this.time = TimeUtil.now();
    }

    public CodeMail(String mail, String code, String time) {
        this.mail = mail;
        this.code = code;
        this.time = time;
    }

    /**
     * 转换为map
     * @return map
     */
    public Map<String, String> toMap(){
        Map<String, String> result = new HashMap<>();
        result.put("mail",mail);
        result.put("code",code);
        result.put("time",time);
        return result;
    }
}
