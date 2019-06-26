package com.xin.admin.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 类功能描述:　fastJson工具类
 *
 * @author Eternal
 * @date 2018/11/8 19:03
 */

public class JsonUtils {

    /**
     * 判断json是否为空
     */
    public static boolean isNotEmpty(JSONObject json) {
        return !json.isEmpty();
    }

    /**
     * json格式化string
     */
    public static String formatToJsonString(Object object){
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }

}
