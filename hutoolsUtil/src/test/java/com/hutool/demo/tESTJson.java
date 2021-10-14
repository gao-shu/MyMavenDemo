package com.hutool.demo;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

/**
 * @Title: tESTJson
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/9 17:25
 */
public class tESTJson {

    private static final String json = "{\"page\":1,\"limit\":15,\"search\":\"\",\"type\":2,\"searchList\":[{\"type\":1,\"values\":[\"22\"],\"formType\":\"text\",\"name\":\"website\"}]}";
    private static final String json2 = "[{\"type\":1,\"values\":[\"22\"],\"formType\":\"text\",\"name\":\"website\"}]";
    @Test
    public void demo1(){
        JSONArray objects = JSONUtil.parseArray(json2);
        System.out.println(objects);
    }

    @Test
    public void demo2(){
        JSONObject jsonObject = JSONUtil.parseObj(json);
        System.out.println(jsonObject);
    }
    
    @Test
    public void demo3(){
        JSONObject jsonObject = JSONUtil.parseObj(json);
        JSONObject jsonObject1 = JSONUtil.parseObj(json);
        System.out.println(jsonObject);
        System.out.println(jsonObject1);
    }
}
