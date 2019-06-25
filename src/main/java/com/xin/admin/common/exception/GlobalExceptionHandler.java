package com.xin.admin.common.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.xin.admin.common.enumeration.ErrorCodeEnum;
import com.xin.admin.common.result.Result;
import com.xin.admin.common.util.HttpRequestUtils;
import com.xin.admin.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.util.List;


/**
 * 类功能描述:　全局处理请求参数校验异常
 *
 * @author Eternal
 * @date 2018/11/7 16:31
 */

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 缺少请求参数(方法级别验证错误，path)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e ,HttpServletRequest request)
    {
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_MISSING_PARAMS.setParam(e.getParameterName()));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }

    /**
     * 参数类型不匹配(方法级别验证错误，path)
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request)
    {
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_TYPE_ERROR.setParam(e.getName(), e.getRequiredType()));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }

    /**
     * 参数校验异常(方法级别验证错误，path)
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result pathValidationException(ConstraintViolationException e, HttpServletRequest request) {
        // 获取报错参数名
        StackTraceElement[] stackTrace = e.getStackTrace();
        String methodName = stackTrace[3].getMethodName();
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_ERROR.setParam(e.getMessage().replace(methodName + ".", "")));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }

    /**
     * Json格式校验失败(实体级别单个参数验证错误，body内参数)
     */
    @ExceptionHandler(value = JsonMappingException.class)
    public Result jsonValidationException(JsonMappingException e, HttpServletRequest request) {
        // 获取报错参数名
        StringBuilder error = new StringBuilder().append(e.getPath().get(0).getFieldName());
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_JSON_TYPE_ERROR.setParam(error));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }


    /**
     * Json格式校验失败(实体级别验证错误，body整体)
     */
    @ExceptionHandler(value = JsonParseException.class)
    public Result jsonValidationException(HttpServletRequest request) {
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_BODY_JSON_TYPE_ERROR);
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }


    /**
     * 参数类型不匹配(实体级别验证错误，body内参数)
     */
    @ExceptionHandler(value = InvalidFormatException.class)
    public Result invalidFormatException(JsonMappingException e, HttpServletRequest request) {
        // 获取报错参数名
        StringBuilder error = new StringBuilder().append(e.getPath().get(0).getFieldName());
        StringBuilder errorMsg = new StringBuilder().append(StringUtils.subString(e.getMessage(),"`","`"));
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_TYPE_ERROR.setParam(error, errorMsg));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }

    /**
     * 参数校验异常(实体级别验证错误，body内参数)
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result bodyValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        // 获取全部报错异常信息
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        StringBuilder error = new StringBuilder();
        allErrors.forEach(
                item-> error.append(item.getField()).append(":").append(item.getDefaultMessage()).append("，")
        );
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_VALIDATION_PARAMS_ERROR.setParam(error.toString().substring(0, error.length() - 1)));
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }

    /**
     * 全局通用异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result globalException(HttpServletRequest request){
        Result result = Result.failure(ErrorCodeEnum.SYS_ERR_GLOBAL);
        HttpRequestUtils.httpLogPrint(request,result);
        return result;
    }
}
