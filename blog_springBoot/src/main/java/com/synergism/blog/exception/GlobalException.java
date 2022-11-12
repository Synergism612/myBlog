package com.synergism.blog.exception;

import com.synergism.blog.exception.custom.*;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalException extends RuntimeException{

    /**
     * 拦截IllegalArgumentException异常
     * 参数校验异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value =IllegalArgumentException.class)
    @ResponseBody
    public Result<Object> IllegalArgumentException(IllegalArgumentException e){
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 拦截IllegalRequestException异常
     * 请求非法异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value = IllegalRequestException.class)
    @ResponseBody
    public Result<Object> IllegalArgumentException(IllegalRequestException e){
        return Result.error(CodeMsg.REQUEST_ILLEGAL.fillArgs(e.getMessage()));
    }

    /**
     * 拦截KeyFailureException异常
     * 密钥失效异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value = KeyFailureException.class)
    @ResponseBody
    public Result<Object> KeyFailureException(KeyFailureException e){
        return Result.error(CodeMsg.KEY_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截PermissionFailureException异常
     * 鉴权失败异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value = PermissionFailureException.class)
    @ResponseBody
    public Result<Object> PermissionFailureException(PermissionFailureException e){
        return Result.error(CodeMsg.PERMISSION_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截SnowFailException异常
     * 雪花算法异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value = SnowFailException.class)
    @ResponseBody
    public Result<Object> SnowFailException(SnowFailException e){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 拦截MailErrorException异常
     * 邮箱发送失败异常
     * @param e 异常
     * @return 结果[null]
     */
    @ExceptionHandler(value = MailErrorException.class)
    @ResponseBody
    public Result<Object> MailErrorException(MailErrorException e){
        return Result.error(CodeMsg.MAIL_ERROR.fillArgs(e.getMessage()));
    }
}
