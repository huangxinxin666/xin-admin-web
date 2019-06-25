package com.xin.admin.common.validator;

import com.xin.admin.common.dictionary.Dict;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述:　校验数据字典实现
 *
 * @author Eternal
 * @date 2019/6/25 15:07
 */

public class ValidDictValidator implements ConstraintValidator<ValidDict, Object> {
    @Override
    public void initialize(ValidDict constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object key, ConstraintValidatorContext constraintValidatorContext) {
        List<Dict> dictList = initDict();
        boolean result = isInDict(key,dictList);
        if(!result && null != dictList && dictList.size()>0){
            //禁用默认提示信息
            constraintValidatorContext.disableDefaultConstraintViolation();
            //更新提示信息
            constraintValidatorContext.buildConstraintViolationWithTemplate(dictDetails(dictList)).addConstraintViolation();
        }
        return result;
    }

    private List<Dict> initDict(){
        //TODO 获取数据源
        List<Dict> dictList = new ArrayList<>();
        Dict dict1 = new Dict();
        dict1.setDictValue("1");
        dict1.setDictValueName("汉族");

        Dict dict2 = new Dict();
        dict2.setDictValue("2");
        dict2.setDictValueName("满族");

        Dict dict3 = new Dict();
        dict3.setDictValue("3");
        dict3.setDictValueName("藏族");

        dictList.add(dict1);
        dictList.add(dict2);
        dictList.add(dict3);
        return dictList;
    }

    private static boolean isInDict(Object key,List<Dict> dictList){

        if (null == key || null == dictList || dictList.size()==0) {
            return false;
        }

        for (Dict aDictList : dictList) {
            if (key.equals(aDictList.getDictValue())) {
                return true;
            }
        }
        return false;

    }

    private static String dictDetails(List<Dict> dictList){
        StringBuilder message = new StringBuilder("数据字典类型范围[ ");
        for (Dict dict : dictList) {
            message.append(dict.getDictValue()).append(":").append(dict.getDictValueName()).append(" ");
        }
        return message + "]";
    }
}
