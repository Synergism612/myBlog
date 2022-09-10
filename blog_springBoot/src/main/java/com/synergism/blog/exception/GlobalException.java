package com.synergism.blog.global.exception;

import com.synergism.blog.global.exception.custom.IllegalRequestException;
import com.synergism.blog.global.exception.custom.KeyFailureException;
import com.synergism.blog.global.exception.custom.PermissionFailureException;
import com.synergism.blog.global.exception.custom.SnowFailException;
import com.synergism.blog.global.result.CodeMsg;
import com.synergism.blog.global.result.Result;
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
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value =IllegalArgumentException.class)
    @ResponseBody
    public Result<Object> IllegalArgumentException(IllegalArgumentException e){
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
    }

    /**
     * 拦截IllegalRequestException异常
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = IllegalRequestException.class)
    @ResponseBody
    public Result<Object> IllegalArgumentException(IllegalRequestException e){
        return Result.error(CodeMsg.REQUEST_ILLEGAL.fillArgs(e.getMessage()));
    }

    /**
     * 拦截KeyFailureException异常
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = KeyFailureException.class)
    @ResponseBody
    public Result<Object> KeyFailureException(KeyFailureException e){
        return Result.error(CodeMsg.KEY_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截PermissionFailureException异常
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = PermissionFailureException.class)
    @ResponseBody
    public Result<Object> PermissionFailureException(PermissionFailureException e){
        return Result.error(CodeMsg.PERMISSION_FAILURE.fillArgs(e.getMessage()));
    }

    /**
     * 拦截SnowFailException异常
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = SnowFailException.class)
    @ResponseBody
    public Result<Object> SnowFailException(SnowFailException e){
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
