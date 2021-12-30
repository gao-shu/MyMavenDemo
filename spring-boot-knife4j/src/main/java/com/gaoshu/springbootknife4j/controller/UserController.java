package com.gaoshu.springbootknife4j.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "客户相关接口")
@ApiOperation("客户相关接口")
public class UserController {

    @GetMapping("/testUser")
    @ApiOperation("测试接口123")
    public void testUser(){
        System.out.println(132);
    }
}

