package com.example.springbootdemo1;


import com.example.springbootdemo1.DemoEnum.DemoEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

public class demo01 {

    @Test
    public void demo(){
        new ArrayList<String>() {{
            add("英语");
        }};
    }


    @Test
    public void demo1(){
        String a = "12";
        if (a != null) {

        }

        System.out.println(234);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Test
    public void demo2(){

    }

    @Test
    public void demo5(){
        String a = "[[{\"Result\":{\"ResponseStatus\":{\"ErrorCode\":500,\"IsSuccess\":false,\"Errors\":[{\"FieldName\":null,\"Message\":\"会话信息已丢失，请重新登录\",\"DIndex\":0}],\"SuccessEntitys\":[],\"SuccessMessages\":[],\"MsgCode\":1}}}]]";
        String[] msgCodes =  a.split("MsgCode");
        System.out.println(msgCodes);
        System.out.println(msgCodes[1]);
        char c = msgCodes[1].charAt(2);
        System.out.println(c == 1);
    }

    @Test
    public void demo6(){
        Date date = new Date();
        System.out.println(date);

//        DateTime dateTime = DateUtil.beginOfDay(DateUtil.date());
//        System.out.println(DateUtil.now());
    }

    @Test
    public void demo7(){
        DemoEnum c3 = DemoEnum.C3;
        System.out.println(c3.toString());
        System.out.println(c3.name());
    }
}
