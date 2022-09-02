package com.synergism.blog.exception;

import com.synergism.blog.exception.custom.IllegalRequestException;
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
}
