package com.xin.admin.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.xin.admin.common.base.Result;
import com.xin.admin.common.enums.ErrorCodeEnum;
import com.xin.admin.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * 类功能描述:　全局处理请求参数校验异常
 *
 * @author Eternal
 * @date 2018/11/7 16:31
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 缺少请求参数(方法级别验证错误，path)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_MISSING_PARAMS.setParam(e.getParameterName()));
    }

    /**
     * 参数类型不匹配(方法级别验证错误，path)
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
    {
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_TYPE_ERROR.setParam(e.getName(), e.getRequiredType()));
    }

    /**
     * 参数校验异常(方法级别验证错误，path)
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result pathValidationException(ConstraintViolationException e) {
        // 获取报错参数名
        StackTraceElement[] stackTrace = e.getStackTrace();
        String methodName = stackTrace[3].getMethodName();
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_ERROR.setParam(e.getMessage().replace(methodName + ".", "")));
    }

    /**
     * Json格式校验失败(实体级别单个参数验证错误，body内参数)
     */
    @ExceptionHandler(value = JsonMappingException.class)
    public Result jsonValidationException(JsonMappingException e) {
        // 获取报错参数名
        StringBuilder error = new StringBuilder().append(e.getPath().get(0).getFieldName());
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_JSON_TYPE_ERROR.setParam(error));
    }


    /**
     * Json格式校验失败(实体级别验证错误，body整体)
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result jsonValidationException() {
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_BODY_JSON_TYPE_ERROR);
    }


    /**
     * 参数类型不匹配(实体级别验证错误，body内参数)
     */
    @ExceptionHandler(value = InvalidFormatException.class)
    public Result invalidFormatException(JsonMappingException e) {
        // 获取报错参数名
        StringBuilder error = new StringBuilder().append(e.getPath().get(0).getFieldName());
        StringBuilder errorMsg = new StringBuilder().append(StringUtils.subString(e.getMessage(),"`","`"));
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_TYPE_ERROR.setParam(error, errorMsg));
    }

    /**
     * 参数校验异常(实体级别验证错误，body内参数)
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result bodyValidationException(MethodArgumentNotValidException e){
        // 获取全部报错异常信息
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        StringBuilder error = new StringBuilder();
        allErrors.forEach(
                item-> error.append(item.getField()).append(":").append(item.getDefaultMessage()).append("，")
        );
        return Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_ERROR.setParam(error.toString().substring(0, error.length() - 1)));
    }

    /**
     * 不支持当前请求方法(http请求验证错误)
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return Result.failure(ErrorCodeEnum.SYS_ERR_HTTP_METHOD_NOT_ALLOWED.setParam(e.getMessage()));
    }

    /**
     * 不支持的媒体类型(http请求验证错误)
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e){
        return Result.failure(ErrorCodeEnum.SYS_ERR_HTTP_UNSUPPORTED_MEDIA_TYPE.setParam(e.getMessage()));
    }

    /**
     * 全局通用异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result globalException(Exception e){
        return Result.failure(ErrorCodeEnum.SYS_ERR_GLOBAL);
    }
}
