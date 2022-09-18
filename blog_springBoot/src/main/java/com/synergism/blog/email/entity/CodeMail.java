package com.synergism.blog.email.entity;

import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CodeMail {
    
    private String mail;
    
    private String code;
    
    private String time;

    public static CodeMail getInstance(Map<String,String> map){
        return new CodeMail(map.get("mail"),map.get("code"),map.get("createTime"));
    }

    public CodeMail(String mail, String code,String time) {
        this.mail = mail;
        this.code = code;
        this.time = time;
    }
}
