package com.example.common.exceptionhandler;

import com.example.common.restutils.RawResponse;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @Author: liuyl
 * @Date: 2020/4/29 11:29
 * @Version: 1.0
 * @Description: 验统一异常处理
 */
@ControllerAdvice
@Order(1)
public class WebExceptionHandler {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    //拦截自定义异常
    @ExceptionHandler(FailException.class)
    @ResponseBody
    public RawResponse FailExceptionHandler(FailException e) {
        return new RawResponse("0001",e.getMessage());
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RawResponse BindExceptionHandler(BindException e) {
        return new RawResponse("0001",e.getBindingResult().getFieldError().getDefaultMessage());
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RawResponse ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        return new RawResponse("0001",e.getMessage());
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RawResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.info("e="+e.toString());
        return new RawResponse("0001",e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
