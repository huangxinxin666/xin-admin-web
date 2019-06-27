package com.xin.admin.config;

import com.xin.admin.common.filter.AccessLogFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 类功能描述:　拦截器配置
 *
 * @author Eternal
 * @date 2019/6/27 11:00
 */
@Configuration
public class FilterConfig {

    @Value("${personal.access-log-filter-enable}")
    private boolean accessLogFilterEnable;

    @Bean
    public Filter accessLogFilter() {
        return new AccessLogFilter();
    }

    @Bean
    public FilterRegistrationBean setAccessLogFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(this.accessLogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(accessLogFilterEnable);
        return filterRegistrationBean;
    }

}
