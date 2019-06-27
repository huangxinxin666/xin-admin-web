package com.xin.admin.common.enums;


/**
 * 枚举功能描述:　性别枚举（getCode、getMsg方法必须有）
 *
 * @author Eternal
 * @date 2018/11/8 15:09
 */

public enum SexEnum {
    /**
     * Sex-男
     */
    SEX_MAN(1,"男"),

    /**
     * Sex-女
     */
    SEX_WOMEN(2,"女");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
