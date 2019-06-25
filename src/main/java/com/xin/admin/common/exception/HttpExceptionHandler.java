package com.xin.admin.common.exception;

import com.xin.admin.common.enumeration.ErrorCodeEnum;
import com.xin.admin.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 类功能描述:　全局处理http请求异常
 *
 * @author Eternal
 * @date 2018/11/8 20:53
 */
@RestControllerAdvice
public class HttpExceptionHandler {

    /**
     * 不支持当前请求方法(http请求验证错误)
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return Result.failure(ErrorCodeEnum.SYS_ERR_HTTP_METHOD_NOT_ALLOWED.setParam(e.getMessage()));
    }

    /**
     * 不支持的媒体类型(http请求验证错误)
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Result handleHttpMediaTypeNotSupportedException(Exception e){
        return Result.failure(ErrorCodeEnum.SYS_ERR_HTTP_UNSUPPORTED_MEDIA_TYPE.setParam(e.getMessage()));
    }

}
