package com.xin.admin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 类功能描述:　自定义配置项
 *
 * @author Eternal
 * @date 2019/6/26 17:58
 */
@Component
@ConfigurationProperties(prefix = "personal")
@Data
public class PersonalPropertiesConfig {

    private boolean accessLogFilterEnable = true;

    private boolean logFormatEnable = false;

    private boolean swaggerEnable = true;

}
