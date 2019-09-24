package com.xin.admin.sys.controller;


import com.xin.admin.common.base.Result;
import com.xin.admin.sys.entity.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


/**
 * 类功能描述:DemoController
 *
 * @author Eternal
 * @date 2018/7/22 0:15
 */
@RestController
@Validated
@Api(value = "测试Swagger2",description="简单的API")
public class DemoController {

    @PostMapping(value = "/api/v2/dm/**")
    public Result emq1(@RequestBody String content){
        System.out.println(content);
        return Result.success();
    }

    @ApiOperation(value="新增Demo", notes="Demo")
    @PostMapping(value = "/demo")
    public Result addDemo(@RequestBody @Valid Demo demo){
        return Result.success(demo.toString());
    }

    @GetMapping("/validate1")
    @ResponseBody
    public String validate1(
            @Size(min = 1,max = 10,message = "姓名长度必须为1到10") @RequestParam("name") String name,
            @Min(value = 10,message = "年龄最小为10") @Max(value = 100,message = "年龄最大为100") @RequestParam("age") Integer age){
        return "validate1";
    }
}
