package com.synergism.blog.result.exception;

import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException extends RuntimeException{

    @ExceptionHandler(value =IllegalArgumentException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(IllegalArgumentException e){
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
    }
}
