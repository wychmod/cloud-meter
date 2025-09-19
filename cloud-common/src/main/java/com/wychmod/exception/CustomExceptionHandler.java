package com.wychmod.exception;

import com.wychmod.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * 用于统一处理系统中的异常，将异常转换为统一的Json格式返回给前端
 * 使用@ControllerAdvice注解标记，可以捕获所有Controller中抛出的异常
 */
@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * 统一异常处理方法
     * 捕获所有类型的Exception异常，并根据异常类型返回不同的Json格式数据
     * 如果是BizException业务异常，则返回业务异常的状态码和信息
     * 如果是其他系统异常，则返回通用的系统异常信息
     *
     * @param e 捕获到的异常对象
     * @return JsonData 统一格式的响应数据
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){

        if(e instanceof BizException bizException){
            log.error("[业务异常]{}",e);
            return JsonData.buildCodeAndMsg(bizException.getCode(),bizException.getMsg());
        }else {
            log.error("[系统异常]{}",e);
            return JsonData.buildError("系统异常");
        }

    }
}
