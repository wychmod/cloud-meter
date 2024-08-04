package com.wychmod.exception;

import com.wychmod.util.JsonFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonFormatter handler(Exception e){

        if(e instanceof BizException bizException){
            log.error("[业务异常]{}",e);
            return JsonFormatter.buildCodeAndMsg(bizException.getCode(),bizException.getMsg());
        }else {
            log.error("[系统异常]{}",e);
            return JsonFormatter.buildError("系统异常");
        }

    }
}
