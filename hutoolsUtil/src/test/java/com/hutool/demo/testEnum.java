package com.hutool.demo;

import cn.hutool.core.util.EnumUtil;
import com.hutool.contant.SexEnum;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Title: testEnum
 * @Description:
 * @author: gaoshu
 * @date: 2021/7/9 16:59
 */
public class testEnum {

    @Test
    public void demo1(){
        List<String> names = EnumUtil.getNames(SexEnum.class);
        names.forEach(System.out::println);
    }
    @Test
    public void demo2(){
        SexEnum sexEnum = EnumUtil.getEnumAt(SexEnum.class, 0);
        SexEnum man = EnumUtil.likeValueOf(SexEnum.class, "MAN");
        Map<String, Object> ASD = EnumUtil.getNameFieldMap(SexEnum.class, "MAN");
        System.out.println(ASD);
    }
    @Test
    public void demo3(){
        String sex = EnumUtil.getEnumAt(SexEnum.class, 0).getSex();
        System.out.println(sex);
    }


}
