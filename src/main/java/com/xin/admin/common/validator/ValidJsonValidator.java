package com.xin.admin.common.validator;


import com.alibaba.fastjson.JSON;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.xin.admin.common.util.JsonUtils.isNotEmpty;

/**
 * 类功能描述:　校验JSON类型实现
 *
 * @author Eternal
 * @date 2018/11/8 18:57
 */

public class ValidJsonValidator implements ConstraintValidator<ValidJson, Object> {

    @Override
    public void initialize(ValidJson constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object jsonObject, ConstraintValidatorContext constraintValidatorContext) {
        if(null == jsonObject){
            return false;
        }
        return isNotEmpty(JSON.parseObject(jsonObject.toString()));
    }
}
