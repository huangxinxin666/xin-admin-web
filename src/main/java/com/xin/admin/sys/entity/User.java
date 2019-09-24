package com.xin.admin.sys.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * 类功能描述:　TODO
 *
 * @author Eternal
 * @date 2019-31-23 8:32
 */
@Data
public class User {

    @PositiveOrZero
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    @Max(200)
    private Integer age;

    @NotBlank
    private String email;
}
