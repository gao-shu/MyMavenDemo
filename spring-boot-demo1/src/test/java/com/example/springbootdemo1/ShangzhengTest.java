package com.example.springbootdemo1;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.example.springbootdemo1.Entity.Shangzheng;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * @author gaoshu
 * @describe shangzheng
 * @date 2022/05/06 17:40
 **/
@SpringBootTest
public class ShangzhengTest {
    private String url = "https://q.stock.sohu.com/hisHq?code=zs_000001&start=20211231&end=20220506&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp&r=0.4250716690870051&0.5181059237584646";

    @Test
    public void test1(){
        String json = HttpUtil.post(url, "");
        HashMap hashMap = BeanUtil.copyProperties(json, HashMap.class);
        List list = BeanUtil.copyProperties(json, List.class);
        Shangzheng shangzheng1 = BeanUtil.copyProperties(json, Shangzheng.class);
        Shangzheng shangzheng = BeanUtil.copyProperties(list.get(0), Shangzheng.class);

        System.out.println(shangzheng);
    }
}
