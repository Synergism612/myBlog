package com.synergism.blog.result;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.util.TimeUtil.now;

/**
 * 结果封装类
 */
@Getter
@Setter
public class Result<T> {

    private int code; //结果代码
    private String msg; //结果消息
    private String time; //响应时间戳
    private T data; //结果数据

    public Result() {
        this.code = 500100;
        this.msg = "服务端异常";
        this.time = now();
        this.data = (T) "";
    }

    /**
     * 无返回数据的请求成功时调用
     * @return 结果
     */
    public static <T> Result<T> success(){
        return new Result<T>(200);
    }

    /**
     * 有返回数据的请求成功时调用
     * @param data 需要返回的数据
     * @return 结果
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(200,data);
    }

    /**
     * 请求错误时调用
     * @param codeMsg 错误封装
     * @return 结果
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg.getCode(), codeMsg.getMsg());
    }

    /**
     * 自定义返回内容时调用
     * @param code 结果代码
     * @param msg 结果消息
     * @param data 结果数据
     * @return 结果
     */
    public static <T> Result<T> result(int code,String msg,T data){
        return new Result<T>(code,msg,data);
    }

    private  Result(int code){
        this.code = code;
        this.msg = "成功";
        this.time = now();
        this.data = (T) "";
    }
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.time = now();
        this.data = (T) "";
    }
    private  Result(int code,T data){
        this.code = code;
        this.msg = "成功";
        this.time = now();
        this.data = (T) data;
    }
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.time = now();
        this.data = (T) data;
    }
}
