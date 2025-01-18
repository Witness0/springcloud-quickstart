package com.witness.order.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice // 统一异常处理
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    public String error(Exception e){
        e.printStackTrace();
        return "服务器出错了";
    }
}
