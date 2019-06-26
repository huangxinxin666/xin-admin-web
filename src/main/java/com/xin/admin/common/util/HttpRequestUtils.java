package com.xin.admin.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述:　HttpServletRequest工具类
 *
 * @author Eternal
 * @date 2019/6/25 18:40
 */

public class HttpRequestUtils {

    private static final String UNKNOWN_IP= "unknown";

    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static void httpLogPrint(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, long responseTime){
        String ipAddress = getIp(requestWrapper);
        String url = requestWrapper.getRequestURL().toString();
        String urlQueryString = requestWrapper.getQueryString();
        if(StringUtils.isNotEmpty(urlQueryString)){
            url+="?"+urlQueryString;
        }
        String method = requestWrapper.getMethod();
        String header = getHeader(requestWrapper);
        String request = getPayLoad(requestWrapper.getContentAsByteArray());
        String response = getPayLoad(responseWrapper.getContentAsByteArray());
        LOG.info("请求来源: {}, 请求地址: {}, 请求方法: {}", LogUtils.logFormatString(ipAddress), LogUtils.logFormatString(url), LogUtils.logFormatString(method));
        LOG.info("请求头部: {}", LogUtils.logFormatJsonString(header));
        if(method.equals(HttpMethod.POST.toString()) || method.equals(HttpMethod.PUT.toString())){
            LOG.info("请求参数: {}", LogUtils.logFormatJsonString(request));
        }
        LOG.info("响应结果: {}", LogUtils.logFormatJsonString(response));
        LOG.info("执行时间: {}", LogUtils.logFormatString(String.valueOf(responseTime) + "ms"));
    }


    /**
     * 获取IP
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取Header
     */
    private static String getHeader(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(16);
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * 获取Body
     */
    private static String getPayLoad(byte[] buf) {
        String payload = "";
        if (null == buf) {
            return payload;
        }
        if (buf.length > 0) {
            try {
                payload = new String(buf, 0, buf.length, Charsets.UTF_8);
            } catch (Exception ex) {
                payload = "[unknown]";
            }
        }
        return payload;
    }

}
