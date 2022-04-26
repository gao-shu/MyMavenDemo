package com.example.springboottest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gaoshu
 * @describe test
 * @date 2022/04/13 17:33
 **/
@Controller
@RequestMapping("/crmActionRecord")
public class test {

    @Autowired
    ShoppingUtil shoppingUtil;

    @RequestMapping("/test")
    public void test(){
        System.out.println(shoppingUtil.getTest());
        System.out.println(shoppingUtil.getHost());
    }
}
