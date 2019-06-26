package com.xin.admin.common.util;

import com.alibaba.fastjson.JSONArray;
import com.xin.admin.config.CustomPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 类功能描述:　日志工具类
 *
 * @author Eternal
 * @date 2019/6/26 17:17
 */
@Component
@PropertySource(value = "classpath:application.yml")
public class LogUtils {

    private static Boolean openLogFormat;

    private final CustomPropertiesConfig customPropertiesConfig;

    @Autowired
    public LogUtils(CustomPropertiesConfig customPropertiesConfig) {
        this.customPropertiesConfig = customPropertiesConfig;
    }

    @PostConstruct
    public void init() {
        openLogFormat = this.customPropertiesConfig.getOpenLogFormat();
    }

    public static String logFormatJsonString(String jsonString){
        if(null != openLogFormat && openLogFormat){
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

    public static String logFormatString(String str){
        if(null != openLogFormat && openLogFormat){
            return "[ " + str + " ]";
        }else {
            return str;
        }
    }


}
