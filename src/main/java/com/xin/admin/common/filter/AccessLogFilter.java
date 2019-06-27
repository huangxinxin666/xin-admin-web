package com.xin.admin.common.filter;

import com.xin.admin.manager.log.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:　Http请求拦截器,日志打印
 *
 * @author Eternal
 * @date 2019/6/25 19:34
 */
public class AccessLogFilter implements Filter {

    private final Logger LOG = LoggerFactory.getLogger(AccessLogFilter.class);

    @Value("${personal.log-format-enable}")
    private boolean logFormatEnable;


    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("Initializing filter 'AccessLogFilter'");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(doFilterEnable(servletRequest)){
            // 缓存body，这样来解决InputStream不能多次读取的问题
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
            // 记录执行时间
            long startTime = System.currentTimeMillis();
            filterChain.doFilter(requestWrapper, responseWrapper);
            long endTime = System.currentTimeMillis();
            // 打印日志
            LogManager.accessLogPrint(requestWrapper,responseWrapper,endTime-startTime,logFormatEnable);
            // 重新写入response
            responseWrapper.copyBodyToResponse();
        }else {
            // 直接放行
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    private boolean doFilterEnable(ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String contentType = request.getContentType();
        String method = request.getMethod();

        if (MediaType.APPLICATION_JSON_VALUE.equals(contentType) || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(contentType)){
            return true;
        }

        if(HttpMethod.POST.toString().equals(method) || HttpMethod.PUT.toString().equals(method)
                || HttpMethod.GET.toString().equals(method) || HttpMethod.DELETE.toString().equals(method)){
            return true;
        }

        return false;
    }
}
