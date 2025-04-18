package com.ningling.globalException;

import com.ningling.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler
    public Result exceptionHandler(PasswordException ce){
        log.error("异常信息：",ce.getMessage());
        return Result.error(ce.getMessage());
    }

    //    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
    //
    //        String message = ex.getMessage();
    //        if(message.contains("Duplicate entry")){
    //            String[] split = message.split(" ");
    //            String username = split[2];
    //            String msg = username + CustomExceptionsConstant.ALREADY_EXISTS;
    //            return Result.error(msg);
    //        }else{
    //            return Result.error(CustomExceptionsConstant.UNKNOWN_ERROR);
    //        }
    //    }
}
