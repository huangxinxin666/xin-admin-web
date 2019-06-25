package com.xin.admin.common.util;

import com.alibaba.fastjson.JSONArray;
import com.xin.admin.common.aspect.LogAspect;
import com.xin.admin.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 类功能描述:　HttpServletRequest工具类
 *
 * @author Eternal
 * @date 2019/6/25 18:40
 */

public class HttpRequestUtils {

    private static final String UNKNOWN_IP= "unknown";

    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static void httpLogPrint(HttpServletRequest request, Result result){
        String ipAddress = HttpRequestUtils.getIp(request);
        String read = getBodyData(request);
        String url = request.getRequestURL().toString();
        JSONArray responses = new JSONArray();
        responses.add(result);
        LOG.info("请求来源: {}, 请求地址: {}", ipAddress, url);
        LOG.info("请求参数: {}", JsonUtils.formatToJsonString(read));
        LOG.info("响应结果: {}", JsonUtils.formatToJsonString(responses));
    }

    /**
     * 获取目标主机的ip
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

    private static  String getBodyData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }

}
