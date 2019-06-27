package com.xin.admin.manager.log;

import com.alibaba.fastjson.JSONArray;
import com.xin.admin.common.util.HttpRequestUtils;
import com.xin.admin.common.util.JsonUtils;
import com.xin.admin.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


/**
 * 类功能描述:　日志工具类
 *
 * @author Eternal
 * @date 2019/6/26 17:17
 */
@Component
public class LogManager {

    private static final Logger LOG = LoggerFactory.getLogger(LogManager.class);

    public static void accessLogPrint(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, long responseTime ,boolean logFormatEnable){
        String ipAddress = HttpRequestUtils.getIp(requestWrapper);
        String url = requestWrapper.getRequestURL().toString();
        String urlQueryString = requestWrapper.getQueryString();
        if(StringUtils.isNotEmpty(urlQueryString)){
            url+="?"+urlQueryString;
        }
        String method = requestWrapper.getMethod();
        String header = HttpRequestUtils.getHeader(requestWrapper);
        String request = HttpRequestUtils.getPayLoad(requestWrapper.getContentAsByteArray());
        String response = HttpRequestUtils.getPayLoad(responseWrapper.getContentAsByteArray());
        LOG.info("请求来源: {}, 请求地址: {}, 请求方法: {}", logFormatString(ipAddress,logFormatEnable), logFormatString(url,logFormatEnable), logFormatString(method,logFormatEnable));
        LOG.info("请求头部: {}", logFormatJsonString(header,logFormatEnable));
        if(method.equals(HttpMethod.POST.toString()) || method.equals(HttpMethod.PUT.toString())){
            LOG.info("请求参数: {}", logFormatJsonString(request,logFormatEnable));
        }
        LOG.info("响应结果: {}", logFormatJsonString(response,logFormatEnable));
        LOG.info("执行时间: {}", logFormatString(String.valueOf(responseTime) + "ms",logFormatEnable));
    }


    private static String logFormatJsonString(String jsonString, boolean logFormatEnable){
        if(logFormatEnable){
            JSONArray json;
            try {
                json = JSONArray.parseArray("[" + jsonString + "]");
            } catch (Exception e) {
                return StringUtils.replaceBlank(jsonString);
            }
            return JsonUtils.formatToJsonString(json);
        }else {
            return StringUtils.replaceBlank(jsonString);
        }
    }

    private static String logFormatString(String str, boolean logFormatEnable){
        if(logFormatEnable){
            return "[ " + str + " ]";
        }else {
            return str;
        }
    }
}
