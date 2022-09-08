package com.synergism.blog.security.authentication.entity;

import com.synergism.blog.exception.custom.PermissionFailureException;
import com.synergism.blog.security.enums.KeyEnum;
import com.synergism.blog.security.enums.RSAEnum;
import com.synergism.blog.security.utils.RSAUtil;
import com.synergism.blog.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;

import static com.synergism.blog.utils.StringUtil.asString;

@Getter
@Setter
public class Auth implements Serializable {
    private String sessionID;
    private String userKey;
    private String userName;
    private String password;
    private String[] power;

    public Auth() {
        this.sessionID = "";
        this.userKey = "";
        this.userName = "";
        this.password = "";
        this.power = new String[]{""};
    }

    public Auth(String sessionID, String userKey, String userName, String password, String[] power) {
        if (StringUtil.checkStringsIfEmpty(sessionID, userKey)) throw new PermissionFailureException("存在空值");
        this.sessionID = sessionID;
        this.userKey = userKey;
        if (StringUtil.checkStringsIfEmpty(userName, password)){
            this.userName = "";
            this.password = "";
        }else {
            this.userName = userName;
            this.password = password;
        }
        this.power = power;
    }

    public static Auth getInstance(HttpServletRequest request) {
        String sessionID = request.getRequestedSessionId();
        String ANOTHER_WORLD_KEY = request.getHeader(asString(KeyEnum.ANOTHER_WORLD_KEY));
        String userKey = RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY, System.getProperty(asString(RSAEnum.PRIVATE_KEY)));

        return new Auth(sessionID,userKey,"","", Power.NOT_LOG_IN.getPower());
    }

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
