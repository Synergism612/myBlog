package com.synergism.blog.exception;

import com.synergism.blog.exception.custom.*;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
@RestController
public class GlobalException extends RuntimeException{

    /**
     * 拦截IllegalArgumentException异常
     * 参数校验异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value =IllegalArgumentException.class)
    @ResponseBody
    public Result<String> IllegalArgumentException(IllegalArgumentException e){
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 拦截IllegalRequestException异常
     * 请求非法异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value = IllegalRequestException.class)
    @ResponseBody
    public Result<String> IllegalArgumentException(IllegalRequestException e){
        return Result.error(CodeMsg.REQUEST_ILLEGAL.fillArgs(e.getMessage()));
    }

    /**
     * 拦截KeyFailureException异常
     * 密钥失效异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value = KeyFailureException.class)
    @ResponseBody
    public Result<String> KeyFailureException(KeyFailureException e){
        return Result.error(CodeMsg.KEY_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截PermissionFailureException异常
     * 鉴权失败异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value = PermissionFailureException.class)
    @ResponseBody
    public Result<String> PermissionFailureException(PermissionFailureException e){
        return Result.error(CodeMsg.PERMISSION_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截SnowFailException异常
     * 雪花算法异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value = SnowFailException.class)
    @ResponseBody
    public Result<String> SnowFailException(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 拦截MailErrorException异常
     * 邮箱发送失败异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(value = MailErrorException.class)
    @ResponseBody
    public Result<String> MailErrorException(MailErrorException e){
        return Result.error(CodeMsg.MAIL_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 拦截MethodArgumentNotValidException异常
     * 参数校验失败异常
     * @param e 异常
     * @return 结果[异常信息]
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message;
        if (allErrors.size()>1) {
            message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("、", "多个条件未达成(", ")"));
        }else{
            message = allErrors.get(0).getDefaultMessage();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
    }
}
