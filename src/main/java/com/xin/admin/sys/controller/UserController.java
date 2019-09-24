package com.xin.admin.sys.controller;


import com.xin.admin.common.base.Result;
import com.xin.admin.sys.entity.Demo;
import com.xin.admin.sys.entity.User;
import com.xin.admin.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 类功能描述:　UserController
 *
 * @author Eternal
 * @date 2019-33-23 8:33
 */
@RestController
@Validated
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value="新增User", notes="User")
    @PostMapping(value = "/user")
    public Result addDemo(@RequestBody @Valid User user){
        return userService.operation();
    }

}
