package com.xin.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.admin.common.base.Result;
import com.xin.admin.sys.entity.User;
import com.xin.admin.sys.mapper.UserMapper;
import com.xin.admin.sys.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类功能描述:　UserServiceImpl
 *
 * @author Eternal
 * @date s 8:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public Result operation() {
        // 新增
        User user = new User();
        user.setName(RandomStringUtils.randomAlphanumeric(10));
        user.setEmail(RandomStringUtils.randomNumeric(8,12)+"@qq.com");
        user.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(2,3)));
        //userMapper.insert(user);

        // 查询
        User user1 = userMapper.selectById(1L);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","T").between("age",10,50);
        //queryWrapper.lambda().eq(User::getAge,20).or().eq(User::getId,5);
        //queryWrapper.lambda().and(obj -> obj.eq(User::getAge,20).eq(User::getId,7));
        List<User> users = userMapper.selectList(queryWrapper);
        return Result.success(user1);
    }
}
