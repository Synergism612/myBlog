package com.synergism.blog.email.entity;

import com.synergism.blog.utils.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CodeMail {
    
    private String mail;
    
    private String code;
    
    private String time;

    /**
     * 从map中读取
     * @param map map
     * @return CodeMail
     */
    public static CodeMail getInstance(Map<String,String> map){
        return new CodeMail(map.get("mail"),map.get("code"),map.get("createTime"));
    }

    public CodeMail() {
        this.mail = "";
        this.code = "";
        this.time = "";
    }

    public CodeMail(String mail, String code,String time) {
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
