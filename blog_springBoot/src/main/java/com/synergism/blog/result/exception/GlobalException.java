package com.synergism.blog.result.exception;

import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
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
}
