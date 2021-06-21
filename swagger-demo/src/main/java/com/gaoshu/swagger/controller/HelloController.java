package com.gaoshu.swagger.controller;

import com.gaoshu.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "欢迎模块")
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/hello1")
    public String hello1(){
        return "hello";
    }

    @PostMapping(value = "/hello2")
    public User user(){
        return new User();
    }

    @ApiOperation("这是欢迎模块方法")
    @PostMapping(value = "/hello3")
    public User user1(@ApiParam("用户名") String username){
        return new User();
    }

    @ApiOperation("这是欢迎模块方法2")
    @PostMapping(value = "/hello4")
    public User user2(@ApiParam(name ="userName", value = "用户名") String username){
        return new User();
    }

}
