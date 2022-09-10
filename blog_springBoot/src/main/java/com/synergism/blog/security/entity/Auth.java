package com.synergism.blog.global.security.authentication.entity;

import com.synergism.blog.global.exception.custom.PermissionFailureException;
import com.synergism.blog.global.security.enums.KeyEnum;
import com.synergism.blog.global.security.enums.RSAEnum;
import com.synergism.blog.global.security.utils.RSAUtil;
import com.synergism.blog.global.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;

import static com.synergism.blog.global.utils.StringUtil.asString;

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
        this.power = new String[]{"login", "register", "public","index"};

    }

    public Auth(String sessionID, String userKey, String userName, String password, String[] power) {
        if (StringUtil.checkStringsIfEmpty(sessionID)) throw new PermissionFailureException("存在空值");
        this.sessionID = sessionID;
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

    public static Auth getInstance(HttpServletRequest request) {
        String sessionID = request.getRequestedSessionId();
        String ANOTHER_WORLD_KEY = request.getHeader(asString(KeyEnum.ANOTHER_WORLD_KEY));
        String userKey = StringUtil.checkStringIfEmpty(ANOTHER_WORLD_KEY) ? "" : RSAUtil.decryptDataOnJava(ANOTHER_WORLD_KEY, System.getProperty(asString(RSAEnum.PRIVATE_KEY)));

        return new Auth(sessionID, userKey, "", "", Power.NOT_LOG_IN.getPower());
    }

    public static Auth BASIC(String sessionID) {
        return new Auth(sessionID, "", "", "", new String[]{"login", "register", "public","index"});
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
