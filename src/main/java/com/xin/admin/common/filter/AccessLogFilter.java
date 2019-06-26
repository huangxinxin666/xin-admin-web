package com.xin.admin.common.filter;

import com.xin.admin.common.util.HttpRequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:　Http请求拦截器,日志打印
 *
 * @author Eternal
 * @date 2019/6/25 19:34
 */
@Component
@WebFilter(filterName = "accessLogFilter", urlPatterns = "/*")
public class AccessLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //缓存body，这样来解决InputStream不能多次读取的问题
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();

        //打印日志
        HttpRequestUtils.httpLogPrint(requestWrapper,responseWrapper,endTime-startTime);

        //重新写入response
        responseWrapper.copyBodyToResponse();
    }

    @Override
    public void destroy() {

    }
}
