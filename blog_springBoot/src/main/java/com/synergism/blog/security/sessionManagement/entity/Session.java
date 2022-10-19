package com.synergism.blog.security.sessionManagement.entity;

import com.synergism.blog.exception.custom.PermissionFailureException;
import com.synergism.blog.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;


/**
 * 会话类
 */
@Getter
@Setter
public class Session implements Serializable {
    //sessionID
    private String sessionID;
    //用户密钥
    private String userKey;
    //登录用户对应主键id
    private String loginID;
    //权限
    private String[] power;

    /**
     * 空参构造函数
     * 序列化
     */
    Session(){}

    /**
     * 构造函数
     * @param sessionID sessionID
     * @param userKey 用户密钥
     * @param loginID 账号
     * @param power 权限
     */
    public Session(String sessionID, String userKey, String loginID, String[] power) {
        //判空
        if (StringUtil.isEmpty(sessionID)) throw new PermissionFailureException("存在空值");
        this.sessionID = sessionID;
        //若没有登录，给空字符串
        if (StringUtil.isEmpty(userKey, loginID)) {
            this.userKey = "";
            this.loginID = "";
        } else {
            this.userKey = userKey;
            this.loginID = loginID;
        }
        this.power = power;
    }

    /**
     * 重写字符串
     * @return 字符串
     */
    @Override
    public String toString() {
        return "User{" +
                "sessionID='" + sessionID + '\'' +
                ", userKey='" + userKey + '\'' +
                ", loginID='" + loginID + '\'' +
                '}';
    }
}
