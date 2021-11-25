package com.gaoshu.controller;

import com.gaoshu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/user")
@Api("客户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/testUser")
    @ApiOperation("测试接口")
    public void testUser(){
        userService.testUser();
    }
}

