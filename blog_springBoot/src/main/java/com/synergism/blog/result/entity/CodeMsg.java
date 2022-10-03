package com.synergism.blog.result.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 错误封装类
 */
@Getter
@Setter
public class CodeMsg {
    private int code; //结果代码
    private String msg; //结果消息
    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(200, "成功");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法：%s");
    public static CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500103, "访问太频繁！");
    public static CodeMsg KEY_FAILURE = new CodeMsg(500104, "密钥失效：%s");
    public static CodeMsg PERMISSION_FAILURE = new CodeMsg(500105, "鉴权失败：%s");
    public static CodeMsg MAIL_ERROR = new CodeMsg(500106, "邮箱异常：%s");


    //登录模块 5002XX
    public static CodeMsg USER_NOT_LOGIN = new CodeMsg(500200, "用户未登录");
    public static CodeMsg TOKEN_INVALID = new CodeMsg(500201, "token无效");
    public static CodeMsg USERNAME_ERROR = new CodeMsg(500202, "账号或密码错误");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(500203, "注册错误：%s");
    public static CodeMsg USER_IS_LOGIN = new CodeMsg(500204, "您已登录");
    public static CodeMsg USER_LOGOUT_ERROR = new CodeMsg(500204, "登出失败");


    /**
     * 填充静态封装的msg参数
     * @param args 填充内容
     * @return 新的msg
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
