package com.xin.admin.sys.entity;

import com.alibaba.fastjson.JSONObject;
import com.xin.admin.common.enumeration.SexEnum;
import com.xin.admin.common.validator.ValidDict;
import com.xin.admin.common.validator.ValidEnum;
import com.xin.admin.common.validator.ValidJson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 类功能描述:Demo实体类
 *
 * @author Eternal
 * @date 2018/7/22 0:20
 */

@Data
@ApiModel("Demo实体")
public class Demo {

    @ApiModelProperty(value = "用户ID",allowableValues = "range[1,10]",required=true)
    @Min(1)
    @Max(10)
    private Integer userId;

    @ApiModelProperty(value = "用户姓名",required = true)
    @NotBlank
    @ValidDict(dictType = "name")
    private String name;

    @ApiModelProperty(value = "性别",allowableValues = "0,1")
    @ValidEnum(enumClass = SexEnum.class)
    private Integer sex;

    @ApiModelProperty("用户密码")
    @Size(max = 6,message = "长度不大于6")
    private String password;

    @ApiModelProperty("用户号码")
    private String phone;

    @ApiModelProperty("用户邮箱")
    @Email
    private String email;

    @ApiModelProperty("JSON格式测试")
    @ValidJson
    private JSONObject jsonTest;
}
