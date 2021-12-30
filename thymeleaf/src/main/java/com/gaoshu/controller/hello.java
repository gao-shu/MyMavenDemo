package com.gaoshu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: hello
 * @Description: thymeleaf测试
 * @author: gaoshu
 * @date: 2021/12/2 16:28
 */
@Controller
public class hello {

    @RequestMapping("/test")
    public String hello(ModelMap model){
        model.addAttribute("name", "张三");
        return "tt";
    }

    @RequestMapping("/test2")
    public String test2(ModelMap model){
        model.addAttribute("name", "张三");
        return "admin/t2";
    }
}
