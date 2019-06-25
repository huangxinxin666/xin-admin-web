package com.xin.admin.common.aspect;

import com.alibaba.fastjson.JSONArray;
import com.xin.admin.common.util.HttpRequestUtils;
import com.xin.admin.common.util.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 类功能描述:　日志切面
 *
 * @author Eternal
 * @date 2019/5/29 9:59
 */

@Aspect
@Component
public class LogAspect {

    private final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.xin.admin.sys.controller.*.*(..))")
    private void logPointCut(){}

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String ipAddress = HttpRequestUtils.getIp(httpServletRequest);
        String url = httpServletRequest.getRequestURL().toString();
        Object[] request = proceedingJoinPoint.getArgs();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        Object response = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();

        JSONArray responses = new JSONArray();
        responses.add(response);
        LOG.info("请求来源: {}, 请求地址: {}", ipAddress, url);
        LOG.info("调用类名: {}, 调用方法名: {}", className, methodName);
        LOG.info("请求参数: {}", JsonUtils.formatToJsonString(request));
        LOG.info("响应结果: {}", JsonUtils.formatToJsonString(responses));
        LOG.info("执行时间：{}ms",endTime-startTime);
        return null;
    }
}
