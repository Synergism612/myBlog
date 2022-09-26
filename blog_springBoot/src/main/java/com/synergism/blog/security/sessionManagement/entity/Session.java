package com.synergism.blog.security.sessionManagement.entity;

import com.synergism.blog.exception.custom.PermissionFailureException;
import com.synergism.blog.security.entity.Power;
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
    //账号
    private String userName;
    //密码
    private String password;
    //权限
    private String[] power;

    /**
     * 构造函数
     * @param sessionID sessionID
     * @param userKey 用户密钥
     * @param userName 账号
     * @param password 密码
     * @param power 权限
     */
    public Session(String sessionID, String userKey, String userName, String password, String[] power) {
        //判空
        if (StringUtil.checkStringsIfEmpty(sessionID)) throw new PermissionFailureException("存在空值");
        this.sessionID = sessionID;
        //若没有登录，给空字符串
        if (StringUtil.checkStringsIfEmpty(userKey, userName, password)) {
            this.userKey = "";
            this.userName = "";
            this.password = "";
        } else {
            this.userKey = userKey;
            this.userName = userName;
            this.password = password;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
