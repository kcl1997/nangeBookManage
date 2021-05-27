package com.kcl.exception;

import com.kcl.pojo.Result;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.exception
 * 文件名   ExceptionHandler
 * 创建者
 * 创建时间: 2021/5/26 9:50 AM
 * 描述  处理全局异常
 */

@RestControllerAdvice
public class GlobalHandler {

    //处理实体类约束异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValllidException(MethodArgumentNotValidException e, HttpServletResponse resp){
        //resp.setStatus(401);
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(401,defaultMessage);
    }


    //处理Json数值转换异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleMessageNotReadable(HttpMessageNotReadableException e, HttpServletResponse resp){
        //写错误码，那么Result就无法返回了
        //resp.setStatus(402);
        //String defaultMessage =  e.getMessage();
        return Result.error(402,"参数数值不符合要求");
    }


}

