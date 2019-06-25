package com.xin.admin.common.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 类功能描述:　字典数据
 *
 * @author Eternal
 * @date 2019/6/25 15:28
 */
@Data
@ApiModel("字典数据")
public class Dict {

    @ApiModelProperty("字典数据")
    private String dictValue;

    @ApiModelProperty("字典数据名称")
    private String dictValueName;
}
