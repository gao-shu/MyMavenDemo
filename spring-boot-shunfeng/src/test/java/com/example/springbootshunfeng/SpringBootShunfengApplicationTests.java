package com.example.springbootshunfeng;

import cn.hutool.http.HttpUtil;
import com.example.springbootshunfeng.common.LengYunUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class SpringBootShunfengApplicationTests {

    @Value("${sfly.url}")
    private String url;

    @Value("${sfly.source}")
    private String source;

    @Value("${sfly.appToken}")
    private String appToken;

    @Value("${sfly.v}")
    private String v;

    @Value("${sfly.userToken}")
    private String userToken;

    @Test
    void contextLoads() {
        Map<String, String> param = new HashMap<>();
        param.put("source", source);
        param.put("appToken", appToken);
        param.put("v", v);
        param.put("userToken", userToken);
        String result = HttpUtil.urlWithForm(url, String.valueOf(param), Charset.defaultCharset(), true);
        log.error(result);
        System.out.println(result);
  }

}
